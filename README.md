In this code base I have solved the case study given by Trivago. 
To complete the case study I have used JetPack libraries provided by google.
The libraries I have used are given below with the reasons:

 *Data Binding: This library binds the UI components with the data models. That is why whenever the 
 data have been changed the UI component the update and adapt the change. Binding adapters are used 
 complete the corresponding changes.   

 *LiveData: LiveData is used to hold data that will be observed by other components e.g., Activity or Fragment.
 I used LiveData to hold viewModel data that will be observed by the Activity because LiveData is lifecycle aware.
 For observing LiveData we have mention a life cycle which activity in my case. That means the LiveData
 observer is remove when the activity is destroyed. With LiveData there is no possibility of memory leaks 
 because of its lifecycle awareness.
 
 *Coroutines: For asynchronous or non-blocking communication with the server I have used kotlin Coroutines. 
 I initiate Coroutine in the ViewModelScope to restrict its leaking. The Coroutines will be stopped 
 when the onCleared method called in the ViewModel. Moreover, I used switch map which execute code inside 
 the live data builder when a live data changed. For example, the implementation of search character it 
 execute api call whenever the search character changes. The same pattern is also used in collecting character 
 detail. However, in collecting character detail information I have to called 3 set of APIs. That is why I used 
 3 coroutines inside a parent coroutine. To start the child I used async keyword which can return a Deferred type.
 Deferred promises to provide data in the future. 3 coroutines started execution of 3 methods in parallel. 
 In two (films and species) of the I have to collect data from a list of urls. To maintain the concurrency among 
 the API calls I convert the list into flow and use flatMapMerge operator which sending request sequentially but
 execute the requests concurrently.   
 
 *Flow: I used flow to collect detailed information from different APIs. Flow backpressure aware that is why
 it can balance the stream production and consumption. I used flow where I have to request and process multiple 
 APIs concurrently because of its backpressure aware nature. 
 
 *Hilt: I used hilt dependency injection library for dependency injection. It reduces boilerplate code regarding
  manual dependency injection or other libraries. It has explicit support regarding android application development 
  with MVVM architecture.
  
 *Recyclerview and Concat Adapter: For both search and detail feature I used recyclerview to show the list of characters
 and their detail information. Recyclerview is memory efficient it does not draw new item every time rather reuse the 
 items that is get out of the screen space. Moreover, In detail activity I used concat adapter to join multiple adapters in one because
 it contains different type of information. For showing each type of information I add an adapter to the concat adapter.
 I think this process is easily scalable. In future if I need to show more information about the character then I have 
 to create and add another adapter to the concat adapter. That requires less modification and more extension.
 
 *Retrofit and Gson: I used retrofit library to create request to the server. It reduces a lot of boilerplate code as well
 as provide great performance. It reduces a lot of development time. Moreover, I use Gson library along with retrofit
 to parse json. Gson convert json to object or entity which can be used for holding data. Moreover, it is very easy
 to use.
 
 *MVVM and clean architecture: I use MVVM and clean architecture to implement the application. Model deals with the data of the 
 application, View deals with layouts and view controllers. ViewModel create the communication between Model and View.
 View observes the data hold by ViewModel and ViewModel get the data from Model. 
 On the other hand, in clean architecture there are three layers presentation, domain and data. Presentation consists
 of view and view related logic (Activities, ViewModel, UiDataModel). It is the most upper layer that directly interacts 
 with the user. Its dependencies are more concrete than other layers. 
 Domain layer connect data and presentation layer. It describes what the application should do. It contains domain entities,
 use cases and repository interface. Entities represents the data holds of the domain. Use cases are special purpose classes,
 each of them defines specific purpose that it can perform. It depends on the abstraction rather concrete. 
 That is why it calls method of repository on an abstract instance. This layer is not aware about where the data will
 come from and who will use it. 
 Data layer provides the data which are needed by the application to perform. In this application, this layer directly communicates
 with remote APIs and collect data. It contains the concrete implementation of repository that request the API via retrofit. The 
 dependencies of this layer is also abstract. 
           
 
     
 
    
         