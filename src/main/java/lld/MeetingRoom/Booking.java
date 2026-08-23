package lld.MeetingRoom;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {
    private final String bookingId;
    private final Employee emp;
    private MeetingRoom room;
    private BookingStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public Booking(Employee emp, MeetingRoom room, LocalDateTime startTime, LocalDateTime endTime) {

        Duration duration = Duration.between(startTime, endTime);

        if(startTime.isAfter(endTime) || !(duration.toMinutes() >= 15 && duration.toMinutes() <= 240)){
            throw new IllegalArgumentException();
        }

        this.bookingId = String.valueOf(UUID.randomUUID());
        this.emp = emp;
        this.room = room;
        this.status = BookingStatus.ACTIVE;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public MeetingRoom getRoom() {
        return room;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getBookingId() {
        return bookingId;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setRoom(MeetingRoom room) {
        this.room = room;
    }
}
