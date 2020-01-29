import java.security.InvalidParameterException;
import java.util.Random;

public class HugeInteger {


	//Array containing the digits of a large number
	private int hugeInt[];
	//Holds the size of the array - number of digits in the number
	private int intSize;
	int sign;
	
	public HugeInteger(String val) {
		//Checking if the value is negative, if yes - must have length > 1
		if (val.charAt(0) == '-' && val.length() > 1) {
			//Initializing the array size for array holding the large value
			intSize = val.length()-1;
			hugeInt = new int[intSize];
			sign = 0;

			//Inputing the integer values of each character into the array
			for (int i=0; i<val.length()-1; i++ ){
				//If the value of the character is a digit then input the digit into the array
				if (Character.isDigit(val.charAt(i+1)) == true) {
					hugeInt[i] = Character.getNumericValue(val.charAt(i+1));
				}
				else {
					throw new InvalidParameterException("One of the values is not a valid number");
				}
			}
		}
		//Checking if it is not negative and not null
		else if (val.length() > 0) {;
			intSize = val.length();
			hugeInt = new int[intSize];
			sign = 1;
			
			//Check if each character in string is an integer then add to array
			for (int i=0; i<val.length(); i++ ){
				if (Character.isDigit(val.charAt(i)) == true) {
					hugeInt[i] = Character.getNumericValue(val.charAt(i));
				}
				else {
					throw new InvalidParameterException("One of the values is not a valid number");
				}
			}
		}
		//Else the value is null
		else {
			throw new InvalidParameterException("There is no value");
		}
	}
	public HugeInteger(int n) {
		if (n>=1) {
			intSize = n;
			hugeInt = new int[n];
			//Making sure the first element is not 0
			//random.nextInt(max - min) + min;
			hugeInt[0] = (new Random().nextInt(10+9) - 9);
			if (hugeInt[0] < 0) {
				sign = 0;
			}
			else {
				sign = 1;
			}
			while (hugeInt[0]==0){
				hugeInt[0] = (new Random() .nextInt(10+9) - 9);
				if (hugeInt[0] < 0) {
					sign = 0;
				}
				else {
					sign = 1;
				}
			}
			//For the rest of the elements, the digit is a number between 0 and 9
			if (n>1) {
				for (int i=1;i<n;i++){
					hugeInt[i] = new Random().nextInt(10);
				}
			}
		}
		else {
			throw new InvalidParameterException("Invalid number of digits");
		}
		hugeInt[0] = Math.abs(hugeInt[0]);
	}

	public HugeInteger add(HugeInteger h){
		int num[];
		int size;
		String regNum = "";
		
		//if both num are +
		if (this.sign == 1 && h.sign == 1){
		//If the current object has more digits, make that the new size
			if (this.intSize >= h.intSize){
				size = this.intSize -1;
				//Counter for shorter array
				int hSize = h.intSize -1;
				num = new int[this.intSize];
				//Iterate through both arrays storing digits, starting at end
				for (int i=size;i>=0;i--){
					//Add the numbers until one of the arrays ends 
					if (hSize >=0){
						num[i] = (this.hugeInt[i] + h.hugeInt[hSize]);
					}
					//The rest of the digits are then just from the larger array
					else {
						num[i] = this.hugeInt[i];
					}
					hSize--;
					//If addition results in overflow, carry the remainder over
					if ((i <size) && (num[i+1]>9)) {
						num[i+1] = (num[i+1] - 10);
						num[i] += 1;
					}
				}
				//Collect the array values and store them in a String
				for (int k=0;k<this.intSize;k++) {
					regNum += Integer.toString(num[k]);
				}
			}
			//Same process, this time h is the larger array
			else if (this.intSize < h.intSize){
				size = h.intSize -1;
				int thisSize = this.intSize -1;
				num = new int[h.intSize];

				for (int i=size;i>=0;i--){
					if (thisSize >=0){
						num[i] = (this.hugeInt[thisSize] + h.hugeInt[i]);
					}
					else {
						num[i] = h.hugeInt[i];
					}
					thisSize--;

					if ((i <size) && (num[i+1]>9)) {
						num[i+1] = (num[i+1] - 10);
						num[i] += 1;
					}
				}
				for (int k=0;k<h.intSize;k++) {
					regNum += Integer.toString(num[k]);
				}
			}
			else {
				throw new InvalidParameterException("Invalid Parameter");
			}
			
		}
		else if (this.sign == 0 && h.sign == 1){
			this.sign = 1;
			HugeInteger res2 = h.subtract(this);
			return res2;
		}
		else if (this.sign == 1 && h.sign == 0){
			h.sign = 1;
			HugeInteger res3 = this.subtract(h);
			return res3;
		}
		//if both are -, treat as normal add then change sign to -
		else if (this.sign == 0 && h.sign == 0){
			this.sign = 1;
			h.sign = 1;
			HugeInteger res4 = this.add(h);
			res4.sign = 0;
			System.out.println("sdsds");
			return res4;
		}
		HugeInteger result = new HugeInteger(regNum);
		return result;
	}
	public HugeInteger subtract(HugeInteger h){
		int size; 
		boolean thisHugeIntBigger;
		String num = "";
		int[] smallArray;
		int[] bigArray;

 		if (this.intSize > h.intSize) {
			size = this.intSize;
			thisHugeIntBigger = true;
			bigArray = this.hugeInt;
			smallArray = h.hugeInt;

		}
		else {
			size = h.intSize;
			thisHugeIntBigger = false;
			bigArray = h.hugeInt;
			smallArray = this.hugeInt;
		}
		int[] array = new int[size];
		if (this.sign == 1 && h.sign == 1){
			int smallSize = smallArray.length-1;
			if ((this.intSize == h.intSize) && this.hugeInt[0]>h.hugeInt[0]){
					
			}


			for (int i = size-1;i>=0;i--){
				if (smallSize>=0) {
					array[i] = bigArray[i] - smallArray[smallSize];
				}
				else {
					array[i] = bigArray[i];
				}
				smallSize--;

				if (i<size-1 && array[i+1]<0){
					array[i+1] += 10;
					array[i] -= 1;
				}
			}
			for (int k=0;k<size;k++) {
				num += Integer.toString(array[k]);
			}
		}
		//if the first num is + and the second is - -> + result
		else if (this.sign == 1 && h.sign == 0) {
			h.sign = 1;
			HugeInteger res2 = this.add(h);
			return res2;
		}
		//if the first num is - and the second is + -> - result
		else if (this.sign == 0 && h.sign == 1) {
			this.sign = 1;
			HugeInteger res3 = this.add(h);
			res3.sign = 0;
			return res3;
		}
		//if both num are -, same situation as before but with ending sign reversed
		else if (this.sign == 0 && h.sign == 0){
			this.sign = 1;
			h.sign = 1;
			HugeInteger res4 = this.subtract(h);
			if (res4.sign == 0) {
				res4.sign = 1;
			}
			else {
				res4.sign = 0;
			}
			return res4;
		}
		HugeInteger result = new HugeInteger(num);
		if (thisHugeIntBigger == true) {
			result.sign = 1;
		}
		else {
			result.sign = 0;
		}
		return result;
	}

	public HugeInteger multiply (HugeInteger h) {
		int size = this.intSize + h.intSize;
		int[] array = new int[size];
		int[] smallArray;
		int[] bigArray;
		int smallsize;
		int bigsize;

		String num = "";

		if (this.intSize > h.intSize){
			bigArray = this.hugeInt;
			smallArray = h.hugeInt;
			smallsize = h.intSize -1;
			bigsize = this.intSize -1;
		}
		else {
			bigArray = h.hugeInt;
			smallArray = this.hugeInt;
			smallsize = this.intSize -1;
			bigsize = h.intSize -1;
		}
		int count = size-1;
		//standard multiplication if both are positive
		if (this.sign == 1 && h.sign == 1){
			//iterate through each digit of larger operand
			for (int i=bigsize;i>=0;i--){
				//multiplying each each digit of smaller operand
				for (int j=smallsize;j>=0;j--){
					//storing it in respective column and shifting column to account for factor of 10 change
					int m = smallsize - j;
					array[count-m] += bigArray[i]*smallArray[j];
				}
				count--;
			}
		//carrying all the remainders over to the front of the number
			int temp = 0;
			for (int i = size-1;i>=0;i--){
				if (array[i] >9 && i >0) {
					temp = array[i];
					array[i] = array[i] % 10;
					temp = temp / 10;
					array[i-1] += temp;
				}
			}
		}
		//if one of the operands is neg and the other is pos -> neg result
		else if ((this.sign == 0 && h.sign == 1) || (this.sign == 1 && h.sign == 0)){
			this.sign = 1;
			h.sign = 1;
			HugeInteger res2 = this.multiply(h);
			res2.sign = 0;
			return res2;
		}
		//if both operands pos -> pos result
		else if (this.sign == 0 && h.sign == 0){
			this.sign = 1;
			h.sign = 1;
			HugeInteger res3 = this.multiply(h);
			return res3;
		}
		for (int k=0;k<size;k++) {
			num += Integer.toString(array[k]);
		}
		HugeInteger result = new HugeInteger(num);
		return result;
	}

		public int compareTo(HugeInteger h) {
			if (this.sign == 1 && h.sign == 0){
				return 1;
			}
			if (this.sign == 0 && h.sign == 1) {
				return -1;
			}
			//both are positive
			if (this.sign == 1 && h.sign == 1){
				//larger positive is larger
				if (this.intSize > h.intSize){
					return 1;
				}
				else if (this.intSize < h.intSize){
					return -1;
				}
				//they are same size
				else {
					for (int i=0;i<this.intSize;i++){
						if (this.hugeInt[i] > h.hugeInt[i]){
							return 1;
						}
						else if (this.hugeInt[i] <h.hugeInt[i]){
							return -1;
						}
						//if they are equal all the way to the last digit then they are the same
						else if (i == this.intSize-1 && (this.hugeInt[i] == h.hugeInt[i])){
							return 0;
						}
					}
				}
			}
			// both are negative
			if (this.sign == 0 && h.sign == 0){
				//smaller negative is larger
				if (this.intSize < h.intSize){
					return 1;
				}
				else if (this.intSize > h.intSize){
					return -1;
				}
				//they are same size
				else {
					for (int i=0;i<this.intSize;i++){
						if (this.hugeInt[i] < h.hugeInt[i]){
							return 1;
						}
						else if (this.hugeInt[i] >h.hugeInt[i]){
							return -1;
						}
						//if they are equal all the way to the last digit then they are the same
						else if (i == this.intSize-1 && (this.hugeInt[i] == h.hugeInt[i])){
							return 0;
						}
					}
				}
			}
			return 1;
	}
	
	public static void main(String[] args) {
		HugeInteger add1 = new HugeInteger("30");
		System.out.println(add1.toString());
		HugeInteger add2 = new HugeInteger("28");
		System.out.println(add2.toString());
		HugeInteger aa = add1.add(add2);
		System.out.print("\nADDING---------------------\n");
		System.out.print(aa.toString());
		System.out.print("\nSUBTRACTING---------------------\n");
		
		HugeInteger ff;
		HugeInteger sub1 = new HugeInteger("32");
		HugeInteger sub2 = new HugeInteger("28");
		ff = sub1.subtract(sub2);
		System.out.println(ff.toString());

		HugeInteger com1 = new HugeInteger("3232");
		HugeInteger com2 = new HugeInteger("-10");
		
		System.out.print("\nCOMPARISON---------------------\n");
		System.out.println("comp: [" + com1.compareTo(com2) + "]");

		HugeInteger ee;
		HugeInteger mult1 = new HugeInteger("-102049444444444444444444444444444444422222222222224999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999992222323232323232333333333321412");
		HugeInteger mult2 = new HugeInteger("-424192929");

		System.out.print("\nMULTI---------------------\n");
		ee = mult1.multiply(mult2);
		System.out.println(ee.toString());

	}
	public String toString(){
		String result = "";
		boolean isZero = true;
		//check if the value is 0, return it without a sign if it is

		for (int i=this.intSize-1;i>=0;i--){
			if (this.hugeInt[i] != 0){
				isZero = false;
			}
		}
		if (isZero == true){
			return "0";
		}
		for (int i=0;i<this.intSize;i++){
			if (!(result == "" && hugeInt[i]==0)){
				result += Integer.toString(this.hugeInt[i]);
			}
		}
		if (this.sign == 0){
			result += " [-]";
		}
		return result;
	}
	
}