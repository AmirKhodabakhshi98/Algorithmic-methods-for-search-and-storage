package Assignment6;

class byteSort {




    private static void insertionSort(int[]input,  int d, int arrStart, int arrEndInclusive,  byte[] str){

        for (int i = arrStart+1; i<= arrEndInclusive; i++){

            int j=i;

            while (j>arrStart &&
                    isPrevBiggerThanNextNew(input[j-1],input[j],d, str)){

                int temp = input[j-1];
                input[j-1] = input[j];
                input[j] = temp;
                j--;
            }
        }
    }


    private static boolean isPrevBiggerThanNextNew(int prev, int next, int d, byte[] str){

        while (getDigit(prev, d, str) == getDigit(next, d, str)) {

            d++;

            if (getDigit(prev,d, str) == -1 &&
                    getDigit(next,d, str) == -1){

                return false;
            }
        }

        return getDigit(prev, d, str) > getDigit(next, d, str);
    }

    private static int getDigit(int elem, int d, byte[] str) {

        if (elem+d < str.length) {
            return str[elem + d];
        } else {
            return -1;
        }
    }




    //d = den position i sträng vi e i
    private static void  Sort(int[]input, int alphabetSize, int[] aux, int lo, int hi, int d,  byte[] str){


        if (hi-lo < 100){
            insertionSort(input,d,lo,hi, str);
            return;
        }

      //  if (hi <= lo){
       //     return;
      //  }

        int[] count = new int[alphabetSize+2]; //+2 för o ha plats för -1 i getdigit

        //frequency
        for (int i=lo; i <= hi; i++){
            int digit = getDigit(input[i],d, str);
            count[digit+2] += 1;
        }

        //cumulatives
        for (int r=0; r<alphabetSize+1;r++){
            count[r+1] += count[r];
        }

        //
        for (int i = lo; i<=hi; i++){
            int digit = getDigit(input[i],d, str)+1; //hämtar värdet för digit
            int position = count[digit]; //hämtar nuvarande position som ska skrivas till
            aux[position] = input[i];
            count[digit]++;
        }


        for (int i=lo; i<=hi; i++){
            input[i] = aux[i-lo];
        }

        //inkl hi
        for (int r=0; r<alphabetSize; r++){
            Sort(input,alphabetSize,aux,lo+count[r],lo+count[r+1]-1,d+1, str);
        }




    }


    protected static void MsdRadixSort(int[]input, int alphabetSize, byte[] str) {

        int[] aux = input.clone();
        Sort(input,alphabetSize,aux,0,input.length-1,0,  str);

    }


}



