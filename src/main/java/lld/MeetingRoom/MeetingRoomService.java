package lld.MeetingRoom;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeetingRoomService {

    private final Map<String,MeetingRoom> rooms;
    private final List<Booking> bookings;
    private final Map<String, Object> roomLocks;

    public MeetingRoomService(Map<String,MeetingRoom> rooms, List<Booking> bookings) {
        this.rooms = rooms;
        this.bookings = bookings;
        this.roomLocks = new HashMap<>();
        for(String roomId : rooms.keySet()){
            this.roomLocks.put(roomId,new Object());
        }

    }



    public Booking bookRoom(Employee emp, String roomId, LocalDateTime startTime, LocalDateTime endTime){
        validateTimeSlot(startTime, endTime);
        if(roomId==null || roomId.isBlank()){
            throw new IllegalArgumentException("Room id is empty");
        }

        //get the room from room id
        MeetingRoom room = rooms.get(roomId);

        if(room==null){
            throw new IllegalArgumentException("Room doesn't exist");
        }

        //get the lock, and check whether room is present or not
        Object lock = roomLocks.get(roomId);
        if(lock == null){
            throw new IllegalArgumentException("Room not found");
        }

        //thread safe locking mechanism
        synchronized (lock) {
            if (isRoomAvailable(roomId, startTime, endTime, null)) {
                //create a new booking
                Booking booking = new Booking(emp, room, startTime, endTime);
                this.bookings.add(booking);
                return booking;
            } else {
                throw new IllegalArgumentException("Booking is conflicting");
            }
        }


    }

    public Booking modifyBooking(String bookingId, String newRoomId, LocalDateTime startTime, LocalDateTime endTime){
        validateTimeSlot(startTime, endTime);
        if (bookingId == null || bookingId.isBlank()) {
            throw new IllegalArgumentException("No booking id provided");
        }
        String existingRoomId = "";
        Booking existingBooking = null;
        //find the booking and fetch roomId
        for(Booking b : this.bookings){
            if(b.getBookingId().equalsIgnoreCase(bookingId)){
                existingBooking = b;
                existingRoomId = b.getRoom().getId();
            }
        }

        if(existingBooking==null || existingRoomId==null || existingRoomId.isEmpty()){
            throw new IllegalArgumentException("Existing booking is corrupted.");
        }

        // new room validations
        if(newRoomId==null || newRoomId.isBlank()){
            throw new IllegalArgumentException("New room id is empty");
        }

        MeetingRoom newRoom = rooms.get(newRoomId);

        if(newRoom==null){
            throw new IllegalArgumentException("New room doesn't exist");
        }
        if(existingRoomId.equalsIgnoreCase(newRoomId)){
            Object lock = roomLocks.get(newRoomId);
            if(lock == null){
                throw new IllegalArgumentException("Room not found");
            }

            //thread safe locking mechanism
            synchronized (lock) {
                if (isRoomAvailable(newRoomId, startTime, endTime, existingBooking)) {
                    return returnModifiedBooking(startTime, endTime, existingBooking, newRoom);
                }
                else{
                    throw new IllegalArgumentException("Booking can't be modifed, as it's clashing");
                }
            }
        }
        else{

            String firstRoomId;
            String secondRoomId;

            if (existingRoomId.compareTo(newRoomId) < 0) {
                firstRoomId = existingRoomId;
                secondRoomId = newRoomId;
            } else {
                firstRoomId = newRoomId;
                secondRoomId = existingRoomId;
            }

            Object firstLock = roomLocks.get(firstRoomId);
            Object secondLock = roomLocks.get(secondRoomId);
            if(firstLock == null || secondLock==null){
                throw new IllegalArgumentException("Room not found");
            }
            //thread safe locking mechanism
            synchronized (firstLock) {
                synchronized (secondLock) {
                    if (isRoomAvailable(newRoomId, startTime, endTime, null)) {
                        return returnModifiedBooking(startTime, endTime, existingBooking, newRoom);
                    } else {
                        throw new IllegalArgumentException("Booking can't be modifed, as it's clashing");
                    }
                }
            }
        }

    }

    public void cancelBooking(String bookingId) {

        if (bookingId == null || bookingId.isBlank()) {
            throw new IllegalArgumentException("No booking id provided");
        }

        Booking existingBooking = null;

        for (Booking b : bookings) {
            if (b.getBookingId().equalsIgnoreCase(bookingId)) {
                existingBooking = b;
                break;
            }
        }

        if (existingBooking == null) {
            throw new IllegalArgumentException("Booking not found");
        }

        String roomId = existingBooking.getRoom().getId();
        Object lock = roomLocks.get(roomId);
        if (lock == null) {
            throw new IllegalArgumentException("Room not found");
        }
        synchronized (lock) {
            existingBooking.setStatus(BookingStatus.CANCELLED);
        }
    }

    private static Booking returnModifiedBooking(LocalDateTime startTime, LocalDateTime endTime, Booking existingBooking, MeetingRoom newRoom) {
        existingBooking.setRoom(newRoom);
        existingBooking.setStartTime(startTime);
        existingBooking.setEndTime(endTime);
        return existingBooking;
    }

    private static void validateTimeSlot(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Start and end times are required");
        }

        long durationMinutes = java.time.Duration.between(startTime, endTime).toMinutes();
        if (!startTime.isBefore(endTime) || durationMinutes < 15 || durationMinutes > 240) {
            throw new IllegalArgumentException("Booking duration must be between 15 and 240 minutes");
        }
    }


    private boolean isRoomAvailable(String roomId, LocalDateTime startTime, LocalDateTime endTime, Booking bookingToExclude) {
        for (Booking b : bookings) {
            if (b == bookingToExclude) {
                continue;
            }
            if (b.getStatus() == BookingStatus.ACTIVE && b.getRoom().getId().equals(roomId) && startTime.isBefore(b.getEndTime()) && endTime.isAfter(b.getStartTime())) {
                return false;
            }
        }
        return true;
    }

}
