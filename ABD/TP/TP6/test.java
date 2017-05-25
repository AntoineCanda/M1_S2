while(previousPage != -1){
				Page CurrentPage = this.mem.loadPage(previousPage);
				previousPage = CurrentPage.getAddresspreviousPage();
				nextPage = CurrentPage.getAddressPage();
				CurrentPage.SetNextAdd(nextPage);
				
			}
