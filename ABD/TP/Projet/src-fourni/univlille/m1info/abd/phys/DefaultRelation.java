package univlille.m1info.abd.phys;

import java.util.ArrayList;

import univlille.m1info.abd.schema.RelationSchema;

public class DefaultRelation {

	private int firstPageAddress = -1;
	private int lastPageAddress = -1;
	private final RelationSchema schema;
	private final MemoryManager mem ; 
	
	public DefaultRelation (RelationSchema schema, MemoryManager mem) {
		this.schema = schema;
		this.mem = mem;
	}
	
	public RelationSchema getRelationSchema () {
		return this.schema;
	}
	
	public int getFirstPageAddress () {
		return firstPageAddress;
	}
	
	public int getLastPageAddress () {
		return lastPageAddress;
	}

	protected void setFirstPageAddress (int pageAddress) {
		this.firstPageAddress = pageAddress;
	}
	
	protected void setLastPageAddress (int pageAddress) {
		this.lastPageAddress = pageAddress;
	}
	
	public void loadTuples(ArrayList<String[]> tuples) {
		// TODO Implement load Tuples into the MemoryManager mem;
		try {
			int i = 0;
			int previousPage = -1;
			int currentPage = -1;
			int nextPage;

			int arity = 0;

			if (!tuples.isEmpty()) {
				arity = tuples.get(0).length;
				Page page = this.mem.NewPage(arity);
				this.setFirstPageAddress(page.getAddressPage());

				while (i < tuples.size()) {

					currentPage = page.getAddressPage();

					boolean ajoutPage = true;

					while (ajoutPage && i < tuples.size()) {

						ajoutPage = page.AddTuple(tuples.get(i));
						if (ajoutPage)
							i++;
					}

					page.SetPrevAdd(previousPage);

					if (i < tuples.size()) {
						previousPage = page.getAddressPage();
						Page page2 = this.mem.NewPage(arity);
						nextPage = page2.getAddressPage();
						page.SetNextAdd(nextPage);
						this.mem.releasePage(currentPage, true);
						page = page2;
					}
					
					else {
						page.SetNextAdd(-1);
						this.mem.releasePage(currentPage, true);
						this.setLastPageAddress(page.getAddressPage());
					}

				}
			} else {
				System.out.println("Il n'y a pas de tuples à charger");
				throw new RuntimeException();
			}

		} catch (NotEnoughMemoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 }