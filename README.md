# eTilbudsavis - Android SDK

## Introduction
This is a short guide to guide you through our Android SDK. We will assume you
are using [eclipse](http://www.eclipse.org/) and the [Android Development Tools](http://developer.android.com/tools/sdk/eclipse-adt.html) plugin. 
Furthermore you are goind to need an API key and secret from [found here](https://etilbudsavis.dk/developers/api/).
Lastly we have included a ETA SDK Demo app in the repository. It demonstrates 
some basic features, some of which are also described in this README.

If you want to get started quickly, just clone the [native-android-sdk](https://github.com/eTilbudsavis/native-android-sdk.git) repository.
Start a new Android Application Project and import the ETA SDK into Eclipse as a library via the menu `Project -> Properties -> Android`.

- [Eta](#Eta)
- [Session](#Session)
- [Api](#Api)
- [Location](#Location)
- [Pageflip](#Pageflip)
- [Shoppinglist Manager](#Shoppinglist Manager)


## Usage

The SDK offers several classes, for different purposes (some contain state, some are worker classes.
Here we will give a short presentation of each class, it's purpose and functionality.
For any information on API specifics please refer to our engineering page: [eta-api](http://engineering.etilbudsavis.dk/eta-api/)


### Eta
This is the main Class. This must be instanciated, before any further calls to the SDK.
The `Eta` class, must also be part of your app's Lifecycle, which means that `onResume()` and `onPause()`
must be called on the `eta` object, this ensures all preferences are saved correctly, and database connections are
opened/closed.

	mEta = new Eta(Keys.API_KEY, Keys.API_SECRET, this);

To ebable the debug output, just set it to `true`:

	mEta.debug(true);

The `eta` object offers several usefull methods, see each Class for details:

- `api()` - get a fresh `Api` object, from which you can easily generate an Api request.
- `getUser()` - Get the current user logged in.
- `getLocation()` - To get the location that the SDK uses for queries, location should always be set, or the API won't respond.
- `getSession()` - The session is automatically generated by the SDK, and is needed for communication with the API.
- `getShoppinglistManager()` - This is a very convinient class, and allows to easily create an shoppinglist for both offline as well as online synchronization.
- `debug(boolean useDebug)` - Use this to enable debugging output to LogCat. NOTE: no debugging output will be displayed, if this isn't set to `true`

We have an array of convenience methods, for the most commonly used methodcalls to the API, all located in the `eta` object.
They all return an `Api` object, that can have further options enabled before `execute()`is called.
Theyare mostly selfexplainatory from their method names, and parameters. And for further details, i'll refer to the JavaDoc.

- `getCatalog` - methods will get some type of `Catalog` either an object or list, based on the exact method.
- `getOffer*` - methods will get some type of `Offer` either an object or list, based on the exact method.
- `getDealer* - methods will get some type of `Dealer` either an object or list, based on the exact method.`
- `getStore*` - methods will get some type of `Store` either an object or list, based on the exact method.
- `searchCatalog*` - search interface for `Catalog`
- `searchOffer*` - search interface for `Offer`
- `searchDealer*` - search interface for `Dealer`
- `searchStore*` - search interface for `Store`



### Session
All API requests require a valid Session, and the session must opdate based on headers from the API. 
Furthermore, Session is a shared state between client and server, and also describes what permissions a given user/session has.

_luckily the SDK takes care of all of this, so you don't have to_ :-)

The commonly used methods in the session are:

- `login()` - For logging in a user
- `loginFacebook()` - For loggingin via Facebook (This requires you to implement the Facebook Android SDK)
- `forgotPassword()` - To retrieve a forgotton password
- `createUser()` - Create a new eTilbudsavis user
- `signout()` - Sign a user out.

Furthermore the Session has a subscriber system, so anyone (class) who want's notification on state change will be notified via an `SessionListener`.
To subscribe/unsubscribe, use `subscribe()` and `unSubscribe()` methods respectively.

<big>Session is not yet intended for a multi user setup</big>

### Api
You can include various options into the api.request() call, just create a Bundle 
with key/value pairs, and send it as a parameter. See more about REST API options
[here](https://etilbudsavis.dk/developers/docs/).

### Location
The EtaLocation object, is a pure state object, and is where you want to store any Location information.
Without a valid location set, the API won't respond with any data, as the whole service is geolocation based.

To set a valid location, you must provide at least a `latitude`, `longitude` and a `radius`.
If you are using `LocationManager` you can pass any new `location` objects directly into `EtaLocation`.

`EtaLocation` will save the last known location to shared preferences, so a valid location is always accessible,
once an initial location have been given.

### Pageflip
Pageflip, is basically just a simple and smooth catalog viewer. With a simple yet effective interface.


The type of events and their corresponding JSON response currently implemented are:

_pagechange_ - When a page changes

- `init` Indicates the very first page change (finished initialization).
- `page` The current page (normalized to the verso).
- `pageLabel` The entire page spread (1-2, 2-3, ..., n or 1, 2, 3, ..., n).
- `pages` Pages currently visible.
- `pageCount` The total amount of single page pages.
- `id` Catalog identifier.

_outofbounds_ - When desired page is out of bounds

- `page` Failed page tried to be reached.
- `direction` In what direction the failed page is in relation to the current page.

_thumbnails_ - Thumbnails dialog toggled 	

- `visible` Whether the dialog is visible or not.

_hotspot_ - A hotspot is clicked

- Offer info.

_singletap_ - A single tap/click

- None.

_doubletap_ - A double tap/click

- None.

_close_ - Pageflip closed

- None.


### Shoppinglist Manager


## Utilities

## Feedback
If you have any feedback or comments feel free to contact danny@etilbudsavis.dk :-)
