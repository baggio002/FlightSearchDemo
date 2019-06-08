# FlightSearchDemo
A demo implements a restful service to search flights by departure time.

Develop an application that will give you available flights for a given search query. You have
an inventory of the following flights:
{
"flights": [
{
"flight": "Air Canada 8099",
"departure": "7:30AM"
},
{
"flight": "United Airline 6115",
"departure": "10:30AM"
},
{
"flight": "WestJet 6456",
"departure": "12:30PM"
},
{
"flight": "Delta 3833",
"departure": "3:00PM"
}
]
}
To search a flight you simply enter the time of the departure. Your flight search
algorithm will only display flights at a distance (plus or minus) of 5 hours from the
time you select for your departure. For example if you search for a 6AM flight, you
will see both the 7:30AM and the 10:30AM flights in the results.
Please implement a REST service to display the flights for different queries with
what you consider would be the best implementation for this scenario.

I implement this scenario with SpringBoot and wrote some testcases.
