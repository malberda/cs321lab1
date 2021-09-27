public class Cache
{
	DLLNode head;

	static class DLLNode			//create node for linked list
	{
		private Object data;
		DLLNode next;

		DLLNode(Object d)
		{
			this.data=d;
			this.next=null;
		}
	}

	public static Cache clarcache(Cache list)//never used or tested
	{
		return null;
	}

	public static Cache addObject(Cache list, Object d)//add a node with data value d to front of linked list
	{
		DLLNode new_node=new DLLNode(d);
		if(list.head==null)
		{
			list.head=new_node;
			return list;
		}
		else
		{
		new_node.next=list.head;
		list.head=new_node;
		return list;
		}
	}

			//remove the last element of the linked list
	public static Cache removeLast(Cache list)
	{
		if(list.head==null||list.head.next==null)
		{
			return null;
		}
		DLLNode second_last=list.head;

int count=1;
		while(second_last.next.next!=null)
		{
			count=count+1;
			second_last=second_last.next;
		}
		second_last.next=null;
	//	System.out.println("emptying the last item from a list of "+count);
		return list;
	}


		//remove an object with data value d.equals(node.data). only works once.
	public static Cache removeObject(Cache list, Object d)
	{
		DLLNode curr=list.head;
		DLLNode prev=curr;
		int count=0;
		if(d.equals(curr.data))
		{
			list.head=curr.next;
			return list;
		}

		while(curr!=null)
		{
			if(d.equals(curr.data))
			{
				prev.next=curr.next;
				curr=null;

				return list;
			}

			prev=curr;
			curr=curr.next;
			count=count+1;
		}
		System.out.println("attempted to remove something that didnt exist in the list");
		return list;
	}

		//returns how many elements are in the list, not index of last one
	public int getCount(Cache list)
	{
			DLLNode curr=list.head;
			int count=0;
		while(curr!=null)
		{
			count=count+1;
			curr=curr.next;
		}
		return count;
	}

		//returns true if element with data d is in list
	public boolean getObject(Cache list, Object d)
	{
		DLLNode curr=list.head;
		while(curr!=null)
		{
			if(d.equals(curr.data))
				return true;
			else
				curr=curr.next;
		}
		return false;
	}





		//used for testing, prints list with header
	public void printList(Cache list)
	{
		DLLNode curr=list.head;
		System.out.println("The linked list is :");
		while(curr!=null)
		{
			System.out.println(curr.data);
			curr=curr.next;
		}
		System.out.println("end of linked list");
	}
}