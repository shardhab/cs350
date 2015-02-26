#!/bin/bash -x

for k in heapsort mergesort quicksort dual_pivot_qs; do
  for j in 1T 5T 10T 50T 100T 500T 1M 5M 10M; do
    java -cp sort_tools sort_tools.SortIO $k/$j.out $k/$j.out.avg
  done
done
