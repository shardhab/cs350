#!/bin/bash -x

for k in heapsort mergesort quicksort dual_pivot_qs; do
  for j in 1T 5T 10T 50T 100T 500T 1M 5M 10M 50M; do
    for i in `seq 1 2`; do 
      java -cp $k $k $j $k/$j.out
    done
  done
done
