##SHIT Color Pickr vertical and horizontal color picker

[ ![Download](https://api.bintray.com/packages/jjhesk/maven/shitcolorpickr/images/download.svg) ](https://bintray.com/jjhesk/maven/shitcolorpickr/_latestVersion)

##Usage

Add view to your activity's layout and specify orientation:
```xml
   <uz.shift.colorpicker.LineColorPicker
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/picker"
        android:layout_width="match_parent"
        android:layout_height="60dp"
        app:orientation="horizontal"/>
```

Optionally you can set colors directly in layout file:
```xml
        app:colors="@array/sample_colors"
        app:selectedColorIndex="3"
```

Setup view
```java
colorPicker = (LineColorPicker) findViewById(R.id.picker);

// set color palette
colorPicker.setColors(new int[] {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW});

// set selected color [optional]
colorPicker.setSelectedColor(Color.RED);

// set on change listener
colorPicker.setOnColorChangedListener(new OnColorChangedListener() {
			@Override
			public void onColorChanged(int c) {
				Log.d(TAG, "Selected color " + Integer.toHexString(c));
			}
		});

// get selected color
int color = colorPicker.getColor();
```

##Downlaod

###Gradle
```gradle
    compile 'com.hkm.ui:shitcolorpickr:1.5.0'
```

###[Releases page](https://github.com/HKMOpen/ShiftColorPicker/releases) 
or grab latest version from repository.

##Requirements

Android 13 and later.

##Todo
* Expand/collapse animations
* More color pickers in different styles
* rounded corners for line picker


![Alt text](/screenshot.png?raw=true)


##License

The MIT License (MIT)

Copyright (c) 2015 Bogdasarov Bogdan 
Copyright (c) 2016 OPENHKM
