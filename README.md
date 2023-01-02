# Introduction 
This project is a Wrapper app around Guest Web, it uses the same application ids (bundle ids) of the native apps so once built these apps could replace any existing apps already published.
It is also using the same release numbering (started back at 2018) which generates a release version number using time/date.  

# Getting Started
1.	Clone the repository
2.	Make sure you have the Key store directories in order to sign release builds

# Add new properties
1. Open the build.gradle file (the app level one found in the GuestWrapper folder)
2. Search for the productFlavors section
3. Add a new productFlavor with the following attributes:
   1. dimension "Property" - Do not change this value
   2. property_code "REDSNPR" - Use the property code from GXP
   3. resValue "string", "app_name", "REDSNPR" - This is the app's name as shown on the phone when browsing through the installed apps
   4. buildConfigField("String", "URL", '"https://guest.sandbox.iris.net/redsnpr"') - The URL pointing to the app
   5. buildConfigField "String", "API_MANAGEMENT_KEY", '"xxxxxxx"' - API Management key
   6. buildConfigField "String", "APP_INTERFACE_TOKEN", '"xxxxx"' - App interface token, generated in GXP
   7. signingConfig signingConfigs.config - This is the app's signing configuration, most apps will use the signingConfigs.config values, few old apps should use the oldConfig instead, see more details below.
   8. applicationId base_application_id + "." + property_code.toLowerCase() - This is the application id, most apps will use it as is however some old apps should have a different one, see details below.
4. Create a new folder named exactly as the productFlavor in the app/src folder (you'll notice other folders exists there already)
5. Copy the res folder with all the assets from the skins repository, make sure the following files are there:
   1. mipmap-xxxx/ic_launcher_round.png - Round app launcher file (used by Google Pixel devices)
   2. mipmap-xxxx/ic_launcher.png - Square app launcher file
   3. drawable/web_hi_res_512.png - Used for the Splash screen
6. Sync Gradle, select the new flavour (left side Build Variants menu) and Build the app

# Branding the Splash screen
The Splash screen is using the splash_logo.png file from the skin folders, when creating new ones important to have it on a transparent background or allow margins around it as it will be displayed with margins in the centre of the screen.
Please note the size of the file should not be more than 300-400 pixels for drawable-xxxhdpi (high resolution screens)

# Updating the Splash screen background color
To update the background color of the splash screen, add a colors.xml file into the app/src/{flavour_name}/res/values (if the values folder doesn't exist create it)
an Example to the file is available in
app/src/hhldnTest/res/values/colors.xml
An example to the content of the file is shown below (red colour):
`
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="splash_background">#ffed1b2e</color>
</resources>
`

# Branding the status bar
In order to change the background colour of the status bar (this is the top bar with the time, WiFi status and battery status) add the following code for the flavour:
`buildConfigField ("String", "StatusBarTextColor", "\"ff000000\"")`
This is not a mandatory field, if not added Android will use  its default colours
Please note he colour code must be in Hex in the format of 8 characters where the 1st 2 (ff in this case) controls the transparency and the last 6 is the color (red in this example)

If you wish to use a light coloured status bar with black text add the following:
`buildConfigField ("boolean", "LightStatusBar", "true")`
By default this field is set to false (white text) and again it is not mandatory to have that as part of the flavour definition


# Supporting old apps

Few apps in the past (originated in the old Mobile Valet web app) had a different application id style and used the old signing configuration. For the properties listed below please use the following:

1. Grand Elysée:
   1. property_code = "GRELHH"
   2. applicationId "com.irisvalet.grelhh.mv" 
   3. signingConfig signingConfigs.oldConfig
2. Beau-Rivage
   1. property_code = "SDBRGS"
   2. applicationId "com.irisvalet.sdbrgs.mv"
   3. signingConfig signingConfigs.oldConfig
3. Hotel Landing
   1. property_code = "HYCHTL"
   2. applicationId "com.irisvalet.hychtl.mv"
   3. signingConfig signingConfigs.oldConfig
4. Chandolin
   1. property_code = "CHNDBH"
   2. applicationId "com.irisvalet.chndbh.mv"
   3. signingConfig signingConfigs.oldConfig
   
# Build the apps - generate APK output files

##Build All apps (release mode)
Run the following in the command line:
`./gradlew assembleRelease`

Once completed the apk files are available in the app/build/outputs/apk folder 
   
##Build a single app
Run the following in the command line:
`./gradlew assemble{flavour name}Release`

Note: flavour name should start with Uppercase, the rest all lower case, example: Productionsoneja
example: ./gradlew assembleProductionsonejaRelease
Once completed the apk files are available in the app/build/outputs/apk folder


# Guest-Wrapper-Android
