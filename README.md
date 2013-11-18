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