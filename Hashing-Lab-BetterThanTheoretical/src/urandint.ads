--URandInt.ADS, based on CACM, “Random Number Generation” by Mauer, Vol. 11, NO. 1, June 65.
generic
  NumBits: Integer;
package urandint is
  -- NumBits determines the range of the pseudo random integers generated  from 1 to ( 2**NumBits - 1).
  -- The random integers are not repeated until the entire range is exhausted.  The pattern of random 
  -- integers may be repeated by calling InitialRandInteger.

  procedure InitialRandInteger;
  function UniqueRandInteger return Integer;
end urandint;
