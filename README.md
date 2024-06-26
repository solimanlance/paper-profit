# My Personal Project


## Project Description

The project that I propose to design will be some sort of 
**virtual** stock market simulator. In the program, the user
will be able to manage a set amount of funds (ex. $10,000), 
and they will be able to choose from some set amount of stocks 
to **purchase** and **sell**. The market will change according to
a database of past stock history, so that there
will be **no calls to any external systems**.

People who use this program may be beginners looking to get in
the stock market that aren't fully confident to invest yet, 
or just those who are looking to have some fun with investing 
simulated money.

This project is of interest to me, as I've always been interested
in investing and the stock market, but I have never actually had
the opportunity to invest any real money (I'm still not over the age of
majority in BC!). I also think that there are many places to go 
with this project, and that it's **scalable** in the sense that many
interesting features can be added as I see fit.

## User Stories
- As a user, I want to be able to add a **stock** to my **portfolio** 
and specify the amount of stock bought. ✔
- As a user, I want to be able to sell stocks from my portfolio
and specify the amount of stock sold. ✔
- As a user, I want to **view** my purchased stocks in my portfolio 
and how much they currently cost. ✔
- As a user, I want to be able to view a list of stocks available to buy 
and their respective prices at the time of purchase. ✔
- As a user, I want to be able to view the amount of money I currently
have in my portfolio and on hand. ✔
- As a user, I want to be able to add money to my total funds multiple times. ✔
- As a user, when I start the application, I want to be given the option to save my
  portfolio to file ✔
- As a user, when I start the application, I want to be given the option to load my 
portfolio from file ✔
- As a user, when I start the application, I want to see a splash screen before the application runs! ✔

# Instructions for Grader
- You can generate the first required action related to the user story 
"adding multiple Xs to a Y" by going into the Buy Stock menu and buying a stock 
at your desired amount.
- You can generate the second required action related to the user story
"adding multiple Xs to a Y" by, after purchasing a stock, going into the
View Portfolio menu and viewing your owned stock.
- You can locate my visual component by starting the application; it's a 'splashscreen'
loading page that is a gif.
- You can save the state of my application by entering your desired name in the Login page, 
going into the Save/Load menu, and clicking on the "Save" button.
- You can reload the state of my application by entering a name (one that has a previous
save state), going into the Save/Load menu, and clicking on the "Load" button.

## Phase 4: Task 2

Sample log:

Tue Apr 02 20:54:28 PDT 2024 
lance bought 3 share(s) of AMZN  
Tue Apr 02 20:54:32 PDT 2024
lance bought 5 share(s) of NVDA \
Tue Apr 02 20:54:40 PDT 2024
lance bought 2 share(s) of META \
Tue Apr 02 20:54:44 PDT 2024
lance added $5000.0 to balance \
Tue Apr 02 20:54:49 PDT 2024
lance sold 2 share(s) of AMZN \
Tue Apr 02 20:54:51 PDT 2024
lance sold 1 share(s) of META \
Tue Apr 02 20:54:53 PDT 2024
lance sold 3 share(s) of NVDA \
Tue Apr 02 20:55:04 PDT 2024
lance added $2500.0 to balance \
Tue Apr 02 20:55:10 PDT 2024
lance bought 7 share(s) of AMZN \
Tue Apr 02 20:55:14 PDT 2024
lance bought 4 share(s) of NVDA \
Tue Apr 02 20:55:24 PDT 2024
lance bought 11 share(s) of META \
Tue Apr 02 20:55:33 PDT 2024
lance sold 4 share(s) of NVDA \
Tue Apr 02 20:55:38 PDT 2024
lance sold 5 share(s) of AMZN \
Tue Apr 02 20:55:41 PDT 2024
lance sold 11 share(s) of META \
Tue Apr 02 20:55:49 PDT 2024
lance added $1225.0 to balance 

## Phase 4: Task 3

Looking at my UML diagram, I would refactor a couple of things about my project
if I had more time to work on it. For one, I would remove all the duplicate code
related to setting font styles and placing elements on the grid in the gui package; I would
simply use one class (potentially a static one) that stores all the ui methods that I need,
so code duplication is avoided. 

Furthermore, I noticed that in my UML diagram a lot of classes have a relationship with
the Stock class, particularly those in the ui package. If I had more time, I would
try refactoring this to avoid the redundant fields in my ui classes and instead
have the market as its own class, holding the stocks in the market. However,
one drawback that this would lead to is that it would make it a bit more tricky
to access these stocks in the market for viewing, buying, and selling.

One last thing that I would change about my project if I had more time is to add
exceptions to the methods in the model classes, instead of having a REQUIRES
clause for various methods, such as the addStock and subtractStock
methods in the Portfolio class. I would do this to make my program more robust, and less
prone to potential bugs or errors. This would, however, make it a lot more tedious
to test, although it would be greatly beneficial to the program.
