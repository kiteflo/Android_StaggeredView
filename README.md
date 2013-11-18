StaggeredView
=============

A simple staggered view for Android :-)

Hint: StaggeredView has been created within Android Studio - this guide describes how to integrate StaggeredView into your Android Studio project. Unfortunately currently AS (0.3.6) does not provide a smooth way of library integration (except you have ten hours left and already 'Master-Of-The-Gradle-Crap') so we quickly describe a programmatic way of embedding this staggered view...

![ScreenShot](/images/staggered.png)

# Quick introduction

In order to use StaggeredView you should be familar with the StaggeredView basic concepts, this enables you to easily extend the component to suit your needs…so let's get started!

There are three different "row" combinations which can occur within the StaggeredView component. A row is one/several images grouped horizontally…see picture below:

![ScreenShot](/images/row.png)

Within a row three different layouts might occurs: a large image only, two small images and a medium sized image (left) or a medium image followed by two small images, see image below:

![ScreenShot](/images/layouts.png)

# Embed StaggeredView

As already said, currently Android Studio and library/module usage is pain in the ass…so my recommendation: simply download this project from git, embed the project locally into your Android Studio and start copying the important parts to the project you wanna use StaggeredView. StaggeredView has been deisgned as a fragment component so there isn't that much copy pasting ;-) The nice thing about downloading the whole project: you have an "up&running" version in your workspace which you can use for experiments, modifications etc…

## Prepare your project

As already said, StaggeredView is a fragment component - so all you need to copy into your project in a first step is the layout file (layout/fragment_staggered_view.xml, paste it into your layout folder…) and the corresponding Java class java/com.jooik.staggeredview.frags.FragmentStaggeredView.java). 

Copy these items to your project and Android will start complaining about missing class files etc., in order to fix these issues of course you need the fragment "business logic", therefore copy&paste the whole java/com.jooik.staggeredview.util package into your project.

Nearly done. In case you wanna use our wonderful cheese images & sentico fonts you should copy main/assets and the drawable-xxhdpi cheese….png files to your project as well. 

Below I have pasted a screenie chalking out all required copy&paste parts:

![ScreenShot](/images/project.png)

## Embed StaggeredView

We expect that you are familar with the concepts of embedding fragments into an application…StaggeredView is embedded programmatically, you can do this for example within the **onCreate** method of your MainActivity:

```java
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentStaggeredView staggeredViewFragment = new FragmentStaggeredView();
        staggeredViewFragment.setItems(initItems());
        staggeredViewFragment.setPadding(15);
        staggeredViewFragment.setBackgroundColor("#000000");
        staggeredViewFragment.setAvoidDuplicateRandomLayouts(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, staggeredViewFragment)
                    .commit();
        }
    }
    …
```
