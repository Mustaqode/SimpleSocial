# SimpleSocial

This is a simple social app which fetches post and comments from the server and display it in the list.

# Functionalities

1) Splash screen with Lottie animation
2) Home screen with two tabs
3) First tab retrieves posts from a public API and display them in a list
4) Second tab has favourite posts (Retrieved from Room DB)
5) When  a post is clicked, comments screen opens and fetches comments for the post from server.
6) There is a favourite button on Comments screen to add the post to favourites
7) You can remove the favourites from Favourites screen
8) The data are cached and can be seen even if the internet is down
9) There is a broadcast reciever to notify user of internet disconnectivity

# Tech Stack

1) Kotlin
2) MVVM (Lifecycle components)
3) Room (For Persistence)
4) Coroutines (For Async programming)
5) Koin for dependency injection
6) Retrofit (For Networking)
7) Gson (For JSON parsing)
8) Lottie (For Animation)
9) Kotlin extension functions for efficiency
10) Kotlin sealed class

#App Demo

[![Simple Social](http://img.youtube.com/vi/SYbjDJXNYy8/0.jpg)](http://www.youtube.com/watch?v=SYbjDJXNYy8 "A simple social app")


