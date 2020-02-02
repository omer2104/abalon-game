


public class TempMain {
	
	public static void main(String[] args) {
		
		/*
		Scanner s=new Scanner(System.in);
		AbalonBoardGame abalon=new AbalonBoardGame(9,5);
		abalon.initBoard();
		System.out.println(abalon.toString());
		Point source,dest;
		int x,y;
		x=s.nextInt();y=s.nextInt();
		source=new Point(x,y);
		x=s.nextInt();y=s.nextInt();
		dest=new Point(x,y);
		while(source.x!=-1 && source.y!=-1)
		{
			abalon.move(source, dest);
			System.out.println(abalon.toString());
			System.out.println("blacks:"+abalon.get_board().get_dataStructure().get_numOfBlacks());
			System.out.println("whites:"+abalon.get_board().get_dataStructure().get_numOfWhites());
			x=s.nextInt();y=s.nextInt();
			source=new Point(x,y);
			x=s.nextInt();y=s.nextInt();
			dest=new Point(x,y);
		}
		*/
		
		/*
		for (int i = 0; i < 9; i++) {
			System.out.println("i- "+i+"::" +abalon.get_board().numOfColsInRow(i));
		}
		*/
		
		/*
		int[][] mat={{0,0,0},{0,0,0},{0,0,0}};
		int[][] mat2=mat.clone();
		
		mat[0][0]=1;
		mat2[0][1]=15;
		System.out.println(mat.toString());
		System.out.println(mat2.toString());
		*/
		
		/*
		AbalonBoardGame abalon=new AbalonBoardGame(9,5);
		abalon.initBoard();
		System.out.println(abalon.toString());
		Collection<Board<AbalonBoardDataStructure,AbalonSoldier[][]>> col= abalon.get_board().getNextStates(Player.WHITE);
		Object[] arr= col.toArray();
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i].toString());
		}
		System.out.println("size of collection:"+col.size());
		*/
		
		
		/*
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				if(abalon.get_board().get_dataStructure().boardDescription()[i][j]!=null)
						System.out.println(abalon.get_board().get_dataStructure().boardDescription()[i][j].name());
						*/
		
		/*
		AbalonBoardGame abalon=new AbalonBoardGame(9,5);
		abalon.initBoard();
		AbalonFrame af=new AbalonFrame(9,5,abalon);
		af.setBoard(abalon.boardDescription());
		*/
		AbalonBoardGameFrame aff=new AbalonBoardGameFrame();
	}

}
