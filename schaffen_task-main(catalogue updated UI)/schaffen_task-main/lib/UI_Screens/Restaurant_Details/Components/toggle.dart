import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:schaffen_task/Provider/provider.dart';

class AnimatedSwitch extends StatefulWidget {


  AnimatedSwitch({Key? key}) : super(key: key);
  @override
  _AnimatedSwitchState createState() => _AnimatedSwitchState();
}

class _AnimatedSwitchState extends State<AnimatedSwitch>
    with SingleTickerProviderStateMixin {

  Duration _duration = const Duration(milliseconds: 370);
  Animation<Alignment>? _animation;
  AnimationController? _animationController;
  @override
  void initState() {
    super.initState();
    _animationController =
        AnimationController(vsync: this, duration: _duration);
    _animation =
        AlignmentTween(begin: Alignment.centerLeft, end: Alignment.centerRight)
            .animate(
          CurvedAnimation(
              parent: _animationController!,
              curve: Curves.bounceOut,
              reverseCurve: Curves.bounceIn),
        );
  }

  @override
  void dispose() {
    _animationController!.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final _counter = Provider.of<CounterModel>(context);
    return AnimatedBuilder(
      animation: _animationController!,
      builder: (context, child) {
        return Center(
          child: GestureDetector(

          onTap: () {

            setState(() {
              if (_animationController!.isCompleted) {
                _animationController!.reverse();
              } else {
                _animationController!.forward();
              }
             _counter.change();
            });},

            child: Container(
              width: 80,
              height: 40,
              padding: const EdgeInsets.fromLTRB(0, 6, 0, 6),
              decoration: BoxDecoration(
                  color: _counter.isVeg ? Colors.green : Colors.red,
                  borderRadius: const BorderRadius.all(
                    const Radius.circular(40),
                  ),
                  boxShadow: [
                    BoxShadow(
                        color: _counter.isVeg ? Colors.green : Colors.red,
                        blurRadius: 12,
                        offset: const Offset(0, 8))
                  ]),
              child: Stack(
                children: <Widget>[
                  Align(
                    alignment: _animation!.value,

                      child: Container(
                        width: 50,
                        height: 40,
                        decoration: const BoxDecoration(
                          shape: BoxShape.circle,
                          color: Colors.white,
                        ),
                      ),
                    ),

                ],
              ),
            ),
          ),
        );
      },
    );
  }
}