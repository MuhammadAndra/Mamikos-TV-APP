#AI Usage Log

Some explanation about my AI usage. I only use Chat GPT for this project. 

##Entries 1 - How to Do Unit Test
1. so to be honest this is the first time i implement Unit Test in my code. so, naturally i ask Chat GPT about how to implement them. in the first chat i ask how to implement unit test in my viewModel? at the first chat i also send both of my ViewModel code alongside with repository code. 
2. after seeing my code Chat GPT gave me an explanation about what is unit test and how to implement them. 
3. after that i start copying the code and import the each library one bye one. but at some point i cant import one of the code line which is advanceUntilIdle(). this code is used to handle Dispatcher.IO lines in my ViewModel.  
4. the IDE said that this is because unresolved refrences. i thought Chat GPT start hallucinating and give me a nonexistent function, and then i check it manually at the documentation. it turns out that advanceUntilIdle() really is a functional code and i only need to add some dependencies in gradle.build file to use it. 

##Entries 2 - How to Add Share Action
1. regarding of this functionality, i vaguely remember that we need to use implicit intent to share a data to another apps, because i have implement this functionality before in college class. so i decided to refresh my knowledge by asking Chat GPT how to implement them. 
2. Chat GPT send me a code on how to implement share action. it is right that we need to use intent to share data to another app. we need the data, context, and intent to start activity and choose which app to send this data for.
3. i accepted the code and test it. it did great.
4. but i decided to put the code a little differently from what is suggested by ChatGPT. so in the code we can put the function directly at onClick Lambda in share button. but i think i should put this in a ViewModel and call the function from View because i think that the purpose of View is only to display Data and we can use view model to manipulate this data. for example sending it to another app. 

##Entries 3 - How to strip HTML code from summary. 
1. as mentioned in PDF instruction, there is HTML tag in summary of every show. so i need to either render it or strip it. i choose to strip it because i think rendering HTML code will need another library implementation that i didn't know how to use
2. i ask chat GPT on how to strip these HTML tag and send the exampple of the summary text.
3. so Chat GPT send me the code and i test them. but i decided to reject the code and give more explanation to Chat GPT
4. that's because after testing the code i found out that the code only delete HTML Tag such as <p> or <b> but didn't delete unicode escaped HTML Tag such as \u003Cp\u003E\u003Cb\u003E. after some more chat i finally able to get the result i want and implemented it on my app. 

##Entries 4 - Refreshing my knowledge about Git
1. after not using Git for a long time i need to refresh my knowledge about Git Feature such as issue, pull request, commit, pull, and how to write in .md files
2. after ChatGPT explained to me, i becamem or informed abaout Git Feature. in my understanding issue is like a problem that needed to be fix/done, pull request is useful if we are working in a team, because someone need to review our code before it can be merge into main, commit is sending some finished code into working branch, and pull is updating what is in Git Repository to our local IDE. lastly Chat GPT also give me some .md text so i can remember what symbol to use when writing an .md file  
3. because there's no code to be review i accept Chat GPT's explanation as it is
4. there's not a thing in this entries that i realized needed to be fix because i use it as an educational purposes. 

##Entries 5 - How to make all screen background the same color
1. according to my design i need to make sure every screen in this app the same color which is 0XFF242A32. i didn't know how to do it so i ask Chat GPT
2. first GPT told me to implement Surface in top level navigation so every navigation after it will also have the same collor
3. but after testing the code i realized that there's no changes in app color. so i send another chat while sending my initial code. Chat GPT then suggested me to whether use the proper solution which is to use theme color or use another method such as hardcode the scaffold color in every screen 
4. in this project i decided to use the second solution because it is faster to just write a single line of code to change background scaffold color than learning how to properly implement background color in application theme. i realized this is not the proper solution but i did it just because this app is simple and needed to be done fast.  
