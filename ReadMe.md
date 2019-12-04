# Sticky Headers RecyclerView

## Introduction
The RecyclerView is a great replacement for the ListView. However, it is not complete, it does not have sectioning. A Google search will give other implementations of RecyclerView with sections. One such implementation override the RecyclerView 'onDraw' method to create sections header, [Sticky Header For RecyclerView
](https://medium.com/@saber.solooki/sticky-header-for-recyclerview-c0eb551c3f68). This author does not feel comfortable overriding the method. Instead, this implementation aim to create sectioning as simple as possible with the use of RelativeView overlaying ability. 

## Require Knowledge
A good understanding and usage of RecyclerView, its adapter and ViewHolder. 

## Implementation

### StickyHeadersRecyclerView
This view, the StickyHeadersRecyclerView (SH-RV) extend RelativeView and wrap a RecyclerView to create a RecyclerView with sticky sections header. The RelativeView is extended to make use of the overlaying ability while the wrapped RecyclerView will perform the recycling feature. The SH-RV also wrapped the RecyclerView adapter to ensure the use of the StickyHeadersRecyclerAdapter (details below). 

The SH-RV implements stickiness by keeping a copy of a view that would be the current section's header-view as 'currentStickyHeaderView'. The adapter StickyHeadersRecyclerAdapter will decide what view is the current section's header-view. The adapter will decide with every scroll of the wrapped RecyclerView. On every scroll, the position (x and y co-ordinate) for the header view will be decided within the SH-RV. If the second child of the wrapped RecyclerView is in a section-header-position, the SH-RV will adjust the 'currentStickyHeaderView.y' value and call 'invalidate' on currentStickyHeaderView to ensure correct positioning of the section heading. 

### StickyHeadersRecyclerAdapter
This adapter, the StickyHeadersRecyclerAdapter (SH-RA) extends the RecyclerView.Adapter and requires six parameters. The first parameter is data type to be displayed in the RecyclerView specified as 'B'. The extension ensure SH-RA can be used in the wrapped RecyclerView. HeaderCapableViewHolder will be the required ViewHolder. 

The second and subsequence parameters include a 'layoutInflator', 'sectionHeaderTextViewLayoutId', 'sectionsHeader', 'sections' and a 'populate' function. The layoutInflator is required to inflate section-header views and their copies. The copies will be used as the 'sticky' header in the RecyclerView. The 'sectionHeaderTextViewLayoutId' is the layout TextView xml that will be used as the section-header views and allow for customisation by other developers. The 'sectionsHeader' parameter is an array of names for all the sections. The 'sections' parameter is an array of arrays of the data type 'B' to be displayed in the SH-RV. There is a runtime requirement that the sizes of sectionsHeader and sections must equal. The final parameter 'populate' function determines how the data get displayed in each itemView of the ViewHolder. 

The SH-RA will also keep a record of the positions when headers should be displayed along with maps of section-header-views and their copies to the positions of the header. The record and maps are created in the initiation of the SH-RA. A count of the 'item' are also calculated in the initiation. 

Specific to this implementation is the finalisation of 'onCreateViewHolder'. The function will create the LinearLayout 'headerCapableLLViewHolder' as required by HeaderCapableViewHolder. The function 'createItemView' will replace the purpose of the original RecyclerView.Adapter's 'onCreateViewHolder' for creating the data-displaying 'itemView'. 

The function 'onBindViewHolder' is also finalised to ensure the checking and displaying of the headers. Apart from 'binding' the data to the holder, this implementation also call on the function 'onBindUpdateWithFeatures' that allow usage to add specific data related events once overriden. 

Due to the section-header-views getting inserted into ViewHolders, a copy of each of the section-header-views get used to create the "sticking" header-views. SH-RA provides the 'getCurrentPositionStickyHeaderView' function for SH-RV to get the current sticking header views. The insertion of header-views into 'itemView' ensure smooth scrolling of the RecyclerView. 

With section-header-views being inserted into any ViewHolder, the 'onViewRecycled' is overrided with the removal of the section-header-views to ensure non-heading positions are displayed correctly (without the header) and ensuring the next insertion will not create an already-have-a-parent exception. 

#### Limitation of StickyHeadersRecyclerAdapter
SH-RA is designed for a specific need and as such, was not designed to work in all possible usage of the RecyclerView. SH-RA have limitations. One limitation is that the data sets of 'sectionsHeader' and 'sections' can not be change and the function 'notifyDataSetChanged' is rendered useless. This one limitation can be remove as long as the header positions and their mapping to header views and copies are calculated with changes to the data sets. 

### HeaderCapableViewHolder
HeaderCapableViewHolder (HC-VH) is a ViewHolder extension that can display a section header within it's itemView. HC-VH requires a 'headerCapableLLItemView', a LinearLayout with a child view that displays a member of an array within the sections. The usage of LinearLayout ensure that a section-header-view can be inserted. Due to the RecyclerView using the first parameter of the ViewHolder initiation as the 'itemView', the headerCapableLLItemView could not be hidden within HC-VH. Instead, the headerCapableLLItemView is the itemView in this implementation.

## Usage / Samples
StickyHeadersRecyclerView at its core is a View and can be use just like other Views. SH-RV can be added to a layout xml as in the 'activity_main.xml' file. There is a restriction with this implementation is that the layout manager has been hard coded to use LinearLayoutManager and it can not be changed. 

Once a reference to a SH-RV is established, the SH-RV is completed with an initiated extension of StickyHeadersRecyclerAdapter as in the MainActivity (line 35) and MainActivity2 (line 13). Developers initiating SH-RA will need to create a TextView xml for the section-header-views, create the dataSets of sectionsHeader and the sections itself as well as a function that populate itemView's child views with the data like 'populateAString' and 'populateWithAPerson'. The TextView xml allows for headers that can be customised. The two samples within this project can be executed by changing the single <activity> tag to use either MainActivity or MainActivity2. 

## Summary
This implementation effectively creates sticky headers RecyclerView. To use this implementation, insert a StickyHeadersRecyclerView into a layout file then extend and initiate a StickyHeadersRecyclerAdapter along with the adapter's parameters. 
