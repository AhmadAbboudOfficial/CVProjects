import 'package:flutter/material.dart';
import 'login_screen.dart';
import 'registration_screen.dart';
import 'package:animated_text_kit/animated_text_kit.dart';
import 'package:flash_chat/components/rounded_button.dart';

class WelcomeScreen extends StatefulWidget {
  static String id = 'welcome_screen';
  @override
  _WelcomeScreenState createState() => _WelcomeScreenState();
}

class _WelcomeScreenState extends State<WelcomeScreen>
    with SingleTickerProviderStateMixin {
  AnimationController controller;
  Animation animation;

  @override
  void initState() {
    super.initState();
    controller = AnimationController(
      duration: Duration(seconds: 1),
      vsync:
          this, //this the ticker,but we have to specify it with SingleTickerProviderStateMixin class
      //to change range of values which it is from 0 to 1, we use lowerBound and upperBound properties
      //and apply the new range of values to any ui component property.
      //upperBound: 100.0,
    );
    //to control range of values in many different ways we use CurvedAnimation
    //and the upperBound should not be larger than 1
    /*
    animation = CurvedAnimation(
      parent: controller, //this the Animation Controller.
      curve: Curves.decelerate, //type of curve which it is the new values range
    );
    */

    //another type of animation called tween animation which the values are in between.
    animation = ColorTween(begin: Colors.blueGrey, end: Colors.white)
        .animate(controller);
    // there many types of tween animation. just define the begin and end.
    //to launch ticker
    controller.forward();
    //what if we want to go backward from large to small so, we use reverse and we specify from property value
    // controller.reverse(from: 1.0);
    //.......
    //to see if the animation is finished or not.
    /*
    animation.addStatusListener((status) {
      //AnimationStatus has different values such as completed(if forward), dismissed(if reverse), forward, reverse
      // and values as a list of AnimationStatus.
      print(status);
    });
     */
    //to follow animation controller ticker
    controller.addListener(() {
      //since using animation controller value to affect scaffold returned in build method to change its
      //background color opacity -->
      setState(() {});
    });
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor:
          Colors.white, //--> Colors.red.withOpacity(controller.value);
      body: Padding(
        padding: EdgeInsets.symmetric(horizontal: 24.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            Row(
              children: <Widget>[
                Hero(
                  tag: 'logo',
                  child: Container(
                    child: Image.asset('images/logo.png'),
                    height:
                        60.0, //using the controller value add animation to logo image height change(animation)
                  ),
                ),
                TypewriterAnimatedTextKit(
                  text: ['Flash Chat'],
                  speed: Duration(milliseconds: 300),
                  textStyle: TextStyle(
                    color: Colors.black,
                    fontSize: 45.0,
                    fontWeight: FontWeight.w900,
                  ),
                ),
              ],
            ),
            SizedBox(
              height: 48.0,
            ),
            RoundedButton(
              color: Colors.lightBlueAccent,
              onPressed: () {
                Navigator.pushNamed(context, LoginScreen.id);
              },
              buttonLabel: 'Log In',
            ),
            RoundedButton(
                color: Colors.blueAccent,
                onPressed: () {
                  Navigator.pushNamed(context, RegistrationScreen.id);
                },
                buttonLabel: 'Register'),
          ],
        ),
      ),
    );
  }
}
