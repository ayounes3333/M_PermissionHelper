# M_PermissionHelper
a helper class for requesting permissions in Android 6.0 Marshmallow (API 23+)

this is an abstract class with three static methods that helps a beginner in android develppement (Like myself) to add support 
to Android 6.0 

<b>boolean isValidPermission(String Permission)</b> Checks if the string in question is a valid permission ie. is a constant string
defined in android.Manifest.permission class

<b>boolean isPermissionGranted(Activity context , String Permission)</b> Checks if the specified permission is granted in the specified context

<b>GetPermission(Activity context , String Permission  , final int REQUEST_CODE , String Explanation)</b> Requests the permission from 
the user
