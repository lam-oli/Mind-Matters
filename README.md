# My Personal Project: UBC CPSC210 

## *Mind Matters*: A Mental Health App

**Introduction:**
Mind Matters is a mental health app used to help anyone who may need it.
Its main purposes are to keep track one's mood, medications, and if they have taken their medication. 
This project is important to me because tech is the way to more accessible and innovative mental health resources. 
I want to attempt to build something that could potentially help people and make an impact on a real issue.


**User Stories:**
- As a user, I want to be able to know if I took my medications on a specific day.
- As a user, I want to add more than one medication to my day.
- As a user, I want to be able to track how many days I've had a specific mood.
- As a user, I want to add a day I updated to my calendar.
- As a user, I want to be able to change whether I took my meds on a given day.
- As a user, I want to know the longest streak I've taken my meds throughout my calendar.
- As a user, I want to delete a day off my calendar.
- As a user, I want to add a journal entry to my day.
- As a user, I want to be able to save my day to my calendar.
- As a user, I want to be able to load my days from my calendar.


**Instructions For User:**

- You can generate the first required action related to adding Xs to a Y by selecting the 'Lets Start a Mind Day' 
button (adds Xs to Ys).
- You can generate the second required action related to adding Xs to a Y by selecting the 'Would You Like to See Your
Mood Streak button, which goes through all the Xs in Ys and counts the days with the given mood.
- You can generate the third action related to adding Xs to a Y by selecting the 'Load' button which will provide a 
table of all the Xs added to Y.
- You can locate my visual component by starting the application! It's a happy mind!
- You can save the state of my application by selecting the save button.
- You can reload the state of my application by selecting the load button.


**Phase 4: Task 2**

My log event works for the "Add a Mind Day", "Save", "Load", and "Check Your Mood Streak" buttons. For example if a user
were to add a Mind Day and then close the app the console should print: "Added a new Mind Day to Calendar". there are 
similar messages for the other buttons that are printed to the console when the application is closed.


**Phase 4: Task 3**

The refactoring I would do for my project is first I would refactor my GUI 100%. First I noticed a lot of repetition
in the creation of each JFrame I added, and I would probably create a new method in solely focused on the creation of
each new JFrame. I would also have cut down on a few of the methods in order to make them less bulky and connected, like 
loadTable() and addMindDay(). I maybe would have also refactored my GUI using the composite pattern because I did a lot 
of JButtons being added to JPanels being added to JFrames, that definitely could've been more cohesive. 
