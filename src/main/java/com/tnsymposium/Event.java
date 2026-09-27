package com.tnsymposium;

public class Event {
    private int id; private String eventName,category,description,eventDate,startTime,venue,city; private double fee; private int availableSeats;
    public Event(int id,String eventName,String category,String description,String eventDate,String startTime,String venue,String city,double fee,int availableSeats){this.id=id;this.eventName=eventName;this.category=category;this.description=description;this.eventDate=eventDate;this.startTime=startTime;this.venue=venue;this.city=city;this.fee=fee;this.availableSeats=availableSeats;}
    public int getId(){return id;} public String getEventName(){return eventName;} public String getCategory(){return category;} public String getDescription(){return description;} public String getEventDate(){return eventDate;} public String getStartTime(){return startTime;} public String getVenue(){return venue;} public String getCity(){return city;} public double getFee(){return fee;} public int getAvailableSeats(){return availableSeats;}
}
