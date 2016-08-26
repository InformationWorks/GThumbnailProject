# GThumb Android Library
 <img src="https://raw.githubusercontent.com/InformationWorks/GThumbnailProject/master/readme_res/cover.png" width=400>
 
 
##Introduction
  This is an android library for thumbnails of any entity e.g. user, institute…..
Do you want to enrich the look of user list or you wish profile screen looks that great even if user has not uploaded own image, then this library gives everything you want in terms of awesome thumbnail. With GThumb, you can load profile picture of entity if explicitly uploaded else set Initials of that entity with colorful backgrounds. Here is an example.

## UI transformation
  GThumb library allows you to replicate the thumb appearance of Android (pure)’s “Contacts” or “GMail” application. Here is a sample how a list UI look without library and with library use.
  
  <img src="https://raw.githubusercontent.com/InformationWorks/GThumbnailProject/master/readme_res/compare.png" width=400>

## Demo
  It is always better to play around features rather than looking at screenshots. Here is a demo app of this library which demonstrates features of this library.
  
  <a href="https://goo.gl/1Fa66Z"><img src="http://www.android.com/images/brand/get_it_on_play_logo_large.png"/></a>

## Documentation

#### How to add in Android project
  - Add gradle dependency to your app’s gradle file.
  
  ````groovy
  dependencies {
    compile 'com.hbb20:gthumb:1.3'
  }
  ````
  
#### Integrate in XML layout
  - In your xml layout add following line to add GThumb.
  
  ````xml
  <com.hbb20.GThumb
  android:id="@+id/gthumb"
  android:layout_width="70dp"
  android:layout_height="70dp"/>
  ````

#### Use in java files (Activities, Fragments, ViewHolders......)
  - Declare a GThumb object by:````Gthumb gthumb;````
  
  - Get from view:````gthumb=(GThumb)findViewById(R.id.gthumb);````

#### Load profile picture along with initials
  - If you want to set single letter of initials in thumb then use ````gthumb.loadThumbForName(imageURL, firstName);````
  Initials will be the first character of ````firstName````. If “Steve” is the passed first name, initials will be “S”.

  - If you want to set two letters of initials in thumb then use ````gthumb.loadThumbForName(imageURL, firstName, secondName);````
  Initials will be the combination of firstName & secondName’s first characters. If “Steve” is first name and “Jobs” is second name then initials will be “SJ”.
  
  - ````imageURL```` is the url of entity's profile picture. GThumb will show Initials until profile pic is loaded. If ````imageURL```` is found null or empty, thumb will not load profile pic there, only initials will be shown. 

#### TextSize
  - Using XML layout
    - Add ````app:gtTextSize```` to the view.
      
      ````xml
      <com.hbb20.GThumb
       android:id="@+id/gthumb"
       android:layout_width="70dp"
       android:layout_height="70dp"
       app:gtTextSize="28sp"/>
      ````
  - Programmatically
    - Use ```` gthumb.setTextSize(textSize); ```` method
    - Here textSize should be in PIXELs
    
#### Colors

  - <b>Multicolor</b>
    - <img src="https://raw.githubusercontent.com/InformationWorks/GThumbnailProject/master/readme_res/normal.png" width=400>
    - In this, thumb background color and text color will be selected from wide array of colors. Library will handle the array internally.
    - This option is enabled by default.
    - Color from array will be picked based on lenght of params passed in ````loadThumbForName();```` method.
    - In case when you had enabled mono color mode and want to switch back to multi color mode use ````gthumb.applyMultiColor();````
  
  - <b>Monocolor</b>
    - <img src="https://raw.githubusercontent.com/InformationWorks/GThumbnailProject/master/readme_res/mono_color.png" width=400>
    - When you want to set single custom color to thumbnail you need to set mono color.
    - using XML
      - Add ````xml app:gtMonoBGColor ```` to layout
        ````xml
        <com.hbb20.GThumb
        android:id="@+id/gthumb"
        android:layout_width="70dp"
        android:layout_height="70dp"
        app:gtMonoBGColor="@color/customBG"
        app:gtMonoTextColor="@color/customText"/>
        ````
          
      - If only ````xml app:gtMonoBGColor ```` is set without ````xml app:gtMonoTextColor ````, then GThumb will use it’s contrast gray scale color as monoTextColor.
      - But only ````xml app:gtMonoTextColor ```` without ```` app:gtMonoBGColor ```` will not enable mono color mode. It will remain default color mode that is multi color mode.
    
    
    - Programmatically
      - Use ```` gthumb.setMonoColor(monoBackgroundColor, monoTextColor); ```` to set custom mono background color along with mono text color. 
      - Use ```` gthumb.setMonoColor(int monoBackgroundColor); ````  , this will set provided color as mono background color and contrast gray scale color will be set as mono text color.
  
  
####Background shape
  
  - <b>Round</b>
    
    <img src="https://raw.githubusercontent.com/InformationWorks/GThumbnailProject/master/readme_res/normal.png" width=400>
    
  - <b>Square</b>
    
    <img src="https://raw.githubusercontent.com/InformationWorks/GThumbnailProject/master/readme_res/square.png" width=400>
  
  - Using XML
    - To set shape from xml layout, add ````xml app:gtBackgroundShape ````
      ````xml
      <com.hbb20.GThumb
       android:id="@+id/gthumb"
       android:layout_width="70dp"
       android:layout_height="70dp"
       app:gtBackgroundShape="square"/> 
      ````
      
    - Round is the default shape.
  
  - Programmatically
    - Use ```` gthumb.setBackgroundShape(GThumb.BACKGROUND_SHAPE gtBackgroundShape);````
    - ```` gtBackgroundShape```` can be either ````GThumb.BACKGROUND_SHAPE.SQUARE```` or ````GThumb.BACKGROUND_SHAPE.ROUND````.
    
####Bold text
  - Lately it is noticed that some standard apps uses bold text for initials too.
  - By default GThumb do not use bold fonts.
  - Set bold font using XML
    - To apply bold text, add  ```` app:gtUseBoldText````
      ````xml
      <com.hbb20.GThumb
       android:id="@+id/gthumb"
       android:layout_width="70dp"
       android:layout_height="70dp"
       app:gtUseBoldText="true"/>
      ````
      
  - Programmatically
    - Use ```` gthumb.setUseBoldText(useBoldText);````.
    - Providing ```` useBoldText=true ```` will set bold text.
    - Providing ```` useBoldText=false ```` will set normal text.


####Intel inside
  - This library uses well known and efficient <a href="http://square.github.io/picasso/">Picasso library</a>, from <a href="http://square.github.io/">Square</a> for image loading.


    







  



  
  

