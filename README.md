# TowerDefense
I already did some stuff with Unity, but I thought it'd be interesting to try to code a game from scratch. (Well not completely, I used JavaFx, but pretty much.)
I also decided to make Towers placable anywhere on a grid, so you can build up paths for the enemies yourself.

## Java-Version
I used the latest Java-Version, namely Java 17. The project can easily be downscaled to Java 11 as I only used the new Switch-Cases from Java 17. I don't think you can go below Java 11, as JavaFX 17 ([see JavaFX](#javafx)) requires Java 11 as a minimum. ([Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))

## Graphics
JavaFX is used for the graphical presantation of the game. I had not heard of GLFW or OpenGL for Java at that point.

### JavaFX
I used version 17.0.0.1. Here is the download of the [SDK](https://download2.gluonhq.com/openjfx/17.0.1/openjfx-17.0.1_windows-x64_bin-sdk.zip), here the [download-overview](https://gluonhq.com/products/javafx/) and the [official page](https://openjfx.io/).

### Art
Two of the graphics are made by my brother (Special Thanks to him):

![Quadruple-Shooter](https://github.com/Serenkii/TowerDefense/blob/main/src/main/resources/com/serenki/art/towers/FourwaysTower.png)
![FireCannon](https://github.com/Serenkii/TowerDefense/blob/main/src/main/resources/com/serenki/art/towers/FireCannon.png)

I took the design of the Zeppelin from [this tutorial](http://www.blackgolem.com/blog/learning-pixel-art-2/). (**THANKS!**) I downscaled it a bit and rotated it:

![Zeppelin](https://github.com/Serenkii/TowerDefense/blob/main/src/main/resources/com/serenki/art/enemies/Zeppelin.png)

All other designs were self-made with [paint.net](https://www.getpaint.net/), although often inspired from (pixel) art from the internet.

## Other
At the moment I've had enough of this project. [2022/01/07]
Many features could be added and improved, but I am happy with what I've accomplished. _(For example how Levels are set up can be majorely improved, but I didn't have a lot of time to make it nice, as I had a deadline till Christmas because I also used this as a present)_
