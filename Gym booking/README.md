# Gym booking application

## Purpose

<p>Everytime I go to the gym, I have to make a booking by directly contacting to the staff. I have always thought that 
 it would be nice to have an application to help manage bookings instead of the staffs. The gym booking application 
 will improve the efficiency of gym booking system. It will enable the gym goers to be able to add or cancel booking, 
 switch booking time and view booking list by day without directly contacting the gym. Gym booking application will 
 be used by everyone with gym membership lead to gym goer's satisfaction enhancement.</p>



##"User Stories"

In the context of a *gym booking* application:
- As a user, I want to be able to add a booking to my booking list
- As a user, I want to be able to cancel a booking from my booking list
- As a user, I want to be able to switch booking time
- As a user, I want to be able to view a list of bookings by day
- As a user, I want to be able to save my booking list to file
- As a user, I want to be able to load my booking list from file

##"Phase 4: Task 2"

- Make appropriate use of Map interface somewhere in your code.
Class where the Map interface used: ListOfBooking 

##"Phase 4: Task 3"

Reflection on refactoring to improve project design:
- I realized that my GUI did not follow the single responsibility principle. It creates the JPanel and adds tools in a 
single class. I will make the GUI class only add the tools to the panels. This will improve cohesion by following the
single responsibility principle. 
- Also, there are a lot of repetitive codes in the GUI class. In order to reduce repetitive codes, I will create a new 
method with the repeating codes and replace the repetition by calling a newly created method.
- I will create a new class named AddBookingField that extends TextField. This will allow me to delete 
AddBookingNameField, AddBookingPhoneNumField, and AddBookingTimeField because all these 3 classes only call the 
constructor of the super class which is TextField. This will make high cohesion and low coupling.