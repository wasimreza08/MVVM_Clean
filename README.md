In this codebase, I have solved the case study given by Trivago. 
To complete the case study I have used JetPack libraries provided by google and features of kotlin language.
The libraries I have used are given below with the reasons:

 *Data Binding: This library binds the UI components with the data models. That is why whenever the 
 data have been changed the UI component updates and adapts the change. Binding adapters are used to 
 complete the corresponding changes.   

 *LiveData: LiveData is used to hold data that will be observed by the ui controllers e.g., Activity or Fragment.
 I used LiveData to hold ViewModel data that will be observed by the Activity because LiveData is lifecycle aware.
 For observing LiveData we have mentioned a life cycle which activity in my case. That means the 
 LiveData observer is removed when the activity is destroyed. With LiveData there is no possibility of memory leaks 
 because of its lifecycle awareness.
 
 *Coroutines: For asynchronous or non-blocking communication with the server I have used kotlin Coroutines. 
 Coroutines are lightweight threads. Multiple coroutines can run in a single thread. 
 I initiate Coroutine in the ViewModelScope to restrict its leaking. The Coroutines will be stopped 
 when the onCleared method called in the ViewModel. Moreover, the coroutines are launched in IO dispatcher
 to get data from the server that will not affect the Main thread. I used a switch map that executes code inside the 
 live data builder when a live data changed. For example, the implementation of search character it execute API call 
 whenever the search character changes. The same pattern is also used in collecting character detail. 
 However, in collecting character detail information I have to call 3 sets of APIs. That is why I used 
 3 coroutines inside a parent coroutine. To start the child I used the async keyword which can return a Deferred type.
 Deferred promises to provide data in the future. 3 coroutines started the execution of 3 methods in parallel. 
 
 *Flow: I used Flow to collect detailed information from different APIs. 
 Flow backpressure aware that is why it can balance the stream production and consumption. I used Flow where I have to 
 request and process multiple APIs concurrently because of its backpressure aware nature. For example, 
 I have to collect data from a list of URLs for films and species. To maintain the concurrency among 
 the API calls I convert the list into the flow and use the flatMapMerge operator which sending request sequentially but
 execute the requests concurrently. I want to mention a link that shows the concurrent operators of 
 flow are in development(https://github.com/Kotlin/kotlinx.coroutines/issues/1147). However, using flatmapmerger
 operator is currently the best option. 
   
 *Hilt: I used the Hilt dependency injection library for dependency injection. 
 It reduces boilerplate code regarding manual dependency injection or other libraries. 
 It has explicit support regarding android application development with MVVM architecture.
   
 *Recyclerview and Concat Adapter: For both search and detail features I used RecyclerView to show 
 the list of characters and their detailed information. RecyclerView is memory efficient it does 
 not draw new item every time rather reuse the items that are getting out of the screen space. 
 Moreover, In the detail activity, I used the Concat adapter to join multiple adapters in one 
 because it contains different types of information. For showing each type of information I add an 
 adapter to the Concat adapter. I think this process is easily scalable. 
 In the future, if I need to show more information about the character then I have to create and 
 add another adapter to the Concat adapter. That requires less modification and more extension.
  
 *Retrofit and Gson: I used the Retrofit library to create requests to get the server data. 
 It reduces a lot of boilerplate code as well as provide great performance. 
 It reduces a lot of development time. Moreover, I use the Gson library along with Retrofit to parse JSON. 
 Gson converts JSON to an object or entity. It is very easy to use.
  
 *MVVM and clean architecture: I use MVVM and clean architecture to implement the application. 
 Model deals with the data of the application, View deals with layouts and view controllers. 
 ViewModel creates the communication between Model and View.
 View observes the data hold by ViewModel and ViewModel gets the data from the Model. 
 On the other hand, in clean architecture, there are three layers presentation, domain, and data. 
 The presentation layer consists of view and view related logic (Activities, ViewModel, UiDataModel). 
 It is the most upper layer that directly interacts with the user. Its dependencies are more concrete 
 than other layers. 
 Domain layer connects data and presentation layer. It describes what the application should do. It contains domain entities,
 use cases, and a repository interface. Entities represent the data holds of the domain. Use cases are special-purpose classes,
 each of them defines a specific purpose that it can perform. It depends on the abstraction rather than concrete. 
 That is why it calls a method of the repository on an abstract instance. This layer is not aware of from where the data will
 come from and who will use it. 
 The data layer provides the data which are needed by the application to perform. 
 This layer directly communicates with remote APIs and collect data. 
 It contains the concrete implementation of the repository that requests the API via retrofit. The 
 dependencies of this layer are also abstract. 
 The application is modularize according to its features.  
 Each feature is separated from others and maintains the architectures. 
 I think it is better for scalability. If a new feature will come it will have a separate set of 
 packages that maintain the architecture and connection with other features. 
 For example, search and detail features are independently following these architectures.
 However, they are connected with each other through activity and the data passing by the intent.
           
 
     
 
    
         