The CountryData App utilizes a http request to the WorldBank API for downloading basic information about a specified number of countries. After finishing the downloads, the information is displayed in the form of a list with the names and flags of the countries.
When the user taps in one country he is directed to another activity that shows detailed information about that country and displays a button to start the GoogleMaps app with the selected country’s latitude and longitude.

#- SplashScreenActivity: This is the initial Activity that introduces the app after it is launched. This activity shows for a brief amount of time and then starts the next Activity, DownloadCountryDataActivity.

#- DownloadCountryDataActivity: This Activity initially displays a download button that when pushed will start a service wich will make a http request to the WorldBank API using an AsyncTask and NetFlix Feign Library Interface. The downloaded information will be in the JSON format and will be used to create a CountryModel object that is saved in the SQLite Database via the insert method from the ContentProvider. The started service will download information for each country and each time the download is finished it will be broadcast back to the activity where the broadCastReceiver will process it and show a notification status bar saying that the download is being done for a specified country. The screen will show the current country that is being downloaded and the countries that the download has already finished. The progress bar will disappear when all downloads have been completed. When there's is no more downloads to be done the user is directed to the started ContryListActivity. 

#The URL that will be used is: http://api.worldbank.org/v2/countries/us?format=json Note that the /us part will change for each country, so a download for information on Japan will take the /jp characters instead.


#- CountryListActivity: On this activity a list of all the availabe countries is shown along whit their names and flags. A ListView is used to hold the Country objects. Each item on the list can be selected and when it is tapped the user is directed to the CountryDetailActivity.

#- CountryDetailActivity: Here the user can see a bigger flag, the name of the country and some basic information that was provided by the WorldBank API. This information is queried through the ContentProvider. There's also a button that when clicked takes the user to the started GoogleMaps App with the selected country's latitude and longitude.

#- ListView:  Customized Android Framework structure do display objects in a list format, each item has a click listener that gets tapping events and directs to a new activity with info on that selected Country object.


#- Country Model: The Country class that holds the getters and setters for Country objects attributes.

#- Content Provider Class: The content provider will manage and access the SQLite Database. It will execute the CRUD operations to store the received data from the API and also query for data to be displayed on the users interfaces.

#- SQLiteOpenHelper: This class has the statements for the DataBase Creation and Version Upgrade, will be managed through the ContentProvider.

#- Interface Requests: Holds the implementation details for the POST and GET http requests to the WorldBank API.
