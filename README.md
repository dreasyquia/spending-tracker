# My Spending Tracker

## What It Does
The spending tracker can:
- **record** purchases, including paid services, made by the user with the price and the date of purchase.
- **categorize** purchases based on the kind of item or service paid for.
- **calculate** how much money was spent in a given month in a given year.

## Who Will Use it
This tracker will be most useful to individuals concerned about their spending habits. Thus, users will be *generally*
those in their twenties or older.

##Why This Project Interests Me
While not too overly complicated, a spending tracker has many possible features that can be added to improve the user's
experience. This is ideal as the project can start off simple and then be incrementally improved upon. As well, I often
have difficulty resisting the urge to spend my money and in turn, keeping track of all my purchases as a university 
student. Therefore, in addition to being a challenging project I wanted to take on, the spending tracker will also be a 
useful application to me after the term is over.

##User Stories
- As a user, I want to be able to record a purchase in my purchase log.
- As a user, I want to be able to see all the purchases I have made.
- As a user, I want to be able to categorize my purchases.
- As a user, I want to be able to calculate how much money I spent in a given month in a given year.
- As a user, I want to be able to save my purchase log to file.
- As a user, I want to be able to load my purchase log from file.
- As a user, I want to be able to clear all the purchases in my purchase log.

##Phase 4: Task 2
Purchase added to purchase log.
Purchase added to purchase log.
Purchase log cleared.
Purchase added to purchase log.
Purchase log cleared.

##Phase 4: Task 3
While there's room for improvement, I am quite proud of my design and believe it is rather decent considering the time
I had to do it. I managed to reduce a significant amount of coupling by having the six button types extend one abstract
button class and giving the TrackerAppGUI one field of a list of buttons, instead of it having six different fields of
each type. Looking back, I'm not sure if I increased or decreased the cohesion of my design by separating AddMenu from
AddButton and ViewMenu from ViewButton. Looking at it one way, they have the same purpose of allowing the user to do a
certain user story. However, I thought it was odd to have a pop-up menu in a class with the word "button." As well, the
purpose that each pair shares can be broken down into two parts: allowing the user to access the functionality with the
button (e.g. AddButton) and allowing the user to fulfill the functionality with the pop-up menu (e.g. AddMenu).