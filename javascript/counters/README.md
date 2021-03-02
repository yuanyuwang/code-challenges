# App Counter

Hi and welcome dear candidate, before anything, we value your time and we don't want you to spend a lot of time working on this. We prepared an small exercise that is divided in stages. Book only a couple of hours of your time and complete all the stages you can. Don't worry if you can't complete everything, this won't be taken in consideration. During the interview we'll discuss, and refactor this kata.


## General instructions
Download the content of this folder and take a look on the code. Solve the stages in a way we can go through the solutions (usually separated commits in a public repository). When you finish the exercise send us back the solution with the link to the repository or with a zipped file via email. We hope you have fun!

## Setup
Run `npm install`, then run `npm start` to start the application and `npm test` to run the tests.

This simple app contains 3 counters and buttons to add and substract numbers every time the buttons are hit, or at least that is what it should be doing. The main goal of this exercise is to provide some basic functionality to this buttons.

## Stage1:
Update the Counter component to take 2 callback methods that will be executed every time each button is clicked. Each callback should take a single, integer value as a parameter which is the amount to increment the counter's existing value by.

## Stage2:
The data array is a global variable. This refactor is about making it only available for App.

## Stage3:
Render a fourth Counter component and ensure it's value is updated independently from the others.

## Stage4:
Give the application some basic styling, it doesn't need to be fancy. The buttons need to have different colours, and should be aligned with
the numbers. Finally add a separator, so it is easy do distinguish the Total Counter from the rest of Counters.

## Stage5:
Create a Total component, which should display below the Counter components and always display the running total of all the Counter values.

## Stage6:
Refactor Counter, so it only accepts one callback method that takes a single integer parameter. Make sure all the Counter ocurrences still update independently

## Stage7:
Add a new component to the main App to establish an initial numbers for the Counters, it can be 0 or it can be a different number, and when the user hits the buttons, the counters should increase accordingly. This button can be hit at any time, so if there is a Counter showing `20` and you establish the new initial number to be `5`, the next time the `+` button is clicked, it should show `6`.
