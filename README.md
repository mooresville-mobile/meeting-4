# meeting-4 - Building a List UI
Code from meeting 4, 01/16/2017

### Summary
We built up some rudimentary UI around the model objects for movies. As of last eeting, we had network
requests going out  and retrieving data from the API, and the ability to parse the responses into 
model objects in code. 

#### iOS
For iOS, here are the changes we're making:

1. Update the existing UI to show a list of movies that are returned from the service response.
    - Add a tableview to the storyboard
    - Update the name to `SearchViewController` for clarity
    - Create a cell for the table
    - Add all UI necessary components
2. Update the view controller to use the network response from meeting 3 to present a list when it
   receives new data
3. Connect the UI on the cell to code to display each model object
4. Show the how the `UITableViewDataSource` protocol works

