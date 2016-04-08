### Integration
``` xml

dependencies {
    compile 'com.cabe.lib.ui:RowView:1.0.4'
}

``` 

### Usage

<img src="./resource/screen_shot.png"  width="400" height="127"/>

#### HorizontalRowView


``` xml

    <com.cabe.lib.ui.rowview.HorizontalRowView
        android:id="@+id/frag_me_row_bank_h"
        style="@style/RowViewStyle"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        app:rv_title="title"
        app:rv_titleDrawablePadding="5dp"
        app:rv_titleDrawable="@drawable/icon_row"
        app:rv_showHint="true"
        app:rv_hintTips="hint"
        android:tag="tag"
        android:onClick="onClick" />
         
```

#### VerticalRowView

``` xml

    <com.cabe.lib.ui.rowview.VerticalRowView
        android:id="@+id/frag_me_row_bank_v"
        style="@style/RowViewStyleV"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        app:rv_title="title"
        app:rv_titleDrawablePadding="5dp"
        app:rv_titleDrawable="@drawable/icon_row"
        app:rv_showHint="true"
        app:rv_hintTips="hint"
        app:rv_innerMargin="2dp"
        android:tag="tag"
        android:onClick="onClick" />
         
```