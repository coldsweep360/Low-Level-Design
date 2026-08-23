package lld.MeetingRoom;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MeetingRoomClient {

    public static void main(String[] args) {

        // Employees
        Employee emp1 = new Employee("E1", "Soham");
        Employee emp2 = new Employee("E2", "Rahul");

        // Meeting rooms
        MeetingRoom room1 = new MeetingRoom("R1", 4);
        MeetingRoom room2 = new MeetingRoom("R2", 8);

        Map<String, MeetingRoom> rooms = new HashMap<>();
        rooms.put(room1.getId(), room1);
        rooms.put(room2.getId(), room2);

        // Service
        MeetingRoomService service = new MeetingRoomService(rooms, new java.util.ArrayList<>());

        LocalDateTime start = LocalDateTime.of(2026, 8, 24, 10, 0);

        LocalDateTime end = LocalDateTime.of(2026, 8, 24, 11, 0);

        // -------------------------
        // 1. Book Room
        // -------------------------

        Booking booking1 = service.bookRoom(emp1, "R1", start, end);

        System.out.println("Booking created: " + booking1.getBookingId());


        // -------------------------
        // 2. Conflicting booking
        // -------------------------

        try {
            service.bookRoom(emp2, "R1", start, end);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected conflict: " + e.getMessage());
        }


        // -------------------------
        // 3. Back-to-back booking
        // -------------------------

        LocalDateTime nextStart = LocalDateTime.of(2026, 8, 24, 11, 0);

        LocalDateTime nextEnd = LocalDateTime.of(2026, 8, 24, 12, 0);

        Booking booking2 = service.bookRoom(emp2, "R1", nextStart, nextEnd);

        System.out.println("Back-to-back booking created: " + booking2.getBookingId());


        // -------------------------
        // 4. Modify booking
        // -------------------------

        LocalDateTime modifiedStart = LocalDateTime.of(2026, 8, 24, 13, 0);

        LocalDateTime modifiedEnd = LocalDateTime.of(2026, 8, 24, 14, 0);

        service.modifyBooking(booking1.getBookingId(), "R2", modifiedStart, modifiedEnd);

        System.out.println("Booking modified to room: " + booking1.getRoom().getId());


        // -------------------------
        // 5. Cancel booking
        // -------------------------

        service.cancelBooking(booking2.getBookingId());

        System.out.println("Booking cancelled: " + booking2.getBookingId());
    }
}