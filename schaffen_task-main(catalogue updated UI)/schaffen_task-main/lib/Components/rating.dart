import 'package:flutter/material.dart';
import 'package:flutter_rating_bar/flutter_rating_bar.dart';
RatingBarIndicator CustomRatingBar({double?rating,double?size=20})
{
  return RatingBarIndicator(
    rating: rating!,
    itemBuilder: (context, index) => const Icon(
      Icons.star,
      color: Colors.amber,
    ),
    itemCount: 5,
    itemSize: size!,
    direction: Axis.horizontal,
  );
}