### Integration
``` xml

dependencies {
    compile 'com.cabe.lib.ui:RowView:lastversion'
}

``` 

### Usage

<img src="./resource/screen_shot.png"  width="300" height="580"/>

#### HorizontalRowView


``` xml

    <com.cabe.lib.ui.rowview.HorizontalRowView
        android:id="@+id/frag_me_row_bank_h"
        style="@style/RowViewStyle"
        app:rv_title="Horizontal Horizontal Title"
        app:rv_titleDrawablePadding="5dp"
        app:rv_titleDrawable="@drawable/rp_icon_me_card"
        app:rv_showHint="true"
        app:rv_labelValue="Horizontal Hint"
        app:rv_showDivider="true"
        app:rv_dividerMarginLeft="16dp"
        app:rv_dividerSize="1dp"
        app:rv_dividerColor="#00FFFF"
        app:rv_titleFixedWidth="100dp"
        app:rv_titleMaxLines="2"
        app:rv_titleEllipsize="End"
        android:tag="Horizontal Tag"
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
        app:rv_labelValue="hint"
        app:rv_innerMargin="2dp"
        android:tag="tag"
        android:onClick="onClick" />

```

#### TipsRowView

``` xml

    <com.cabe.lib.ui.rowview.TipsRowView
        android:id="@+id/frag_me_row_bank_v"
        style="@style/RowViewStyleV"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        app:rv_title="title"
        app:rv_titleDrawablePadding="5dp"
        app:rv_titleDrawable="@drawable/icon_row"
        app:rv_showHint="true"
        app:rv_labelValue="hint"
        app:rv_innerMargin="2dp"
        app:rv_tips_1="tips1"
        app:rv_tips_2="tips2"
        android:tag="tag"
        android:onClick="onClick" />

```

#### GapLineView

``` xml

    <com.cabe.lib.ui.line.DashLineView
        android:id="@+id/frag_me_line_gap_h"
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:layout_marginTop="10dp"
        app:gl_dashWidth="8dp"
        app:gl_dashColor="#FF0000"
        app:gl_gapWidth="6dp"
        app:gl_gapColor="#0000FF00" />

```


#### TitleLabelLayout
``` xml

    <com.cabe.lib.ui.label.TitleLabelLayout
        android:layout_width="match_parent"
        android:layout_height="100dp"
        android:paddingLeft="10dp"
        android:paddingRight="10dp"
        android:gravity="center_vertical"
        app:tl_title="app_nameapp_namenapp_nasdfsdffsdmeasdf"
        app:tl_titleColor="#333333"
        app:tl_titleSize="16dp"
        app:tl_titlePadding="20dp"
        app:tl_titleMaxLines="2"
        app:tl_titleEllipsize="End"
        app:tl_titleFixedWidth="140dp"
        app:tl_titleGravity="Center"
        app:tl_titleLayoutGravity="Center"
        app:tl_titleDrawable="@drawable/rp_icon_me_card"
        app:tl_titleDrawableWidth="40dp"
        app:tl_titleDrawableHeight="40dp"
        app:tl_titleDrawablePadding="10dp"
        app:tl_titleDrawableGravity="Center" >

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/app_name"
            android:textColor="#FF0000" />

    </com.cabe.lib.ui.label.TitleLabelLayout>

```