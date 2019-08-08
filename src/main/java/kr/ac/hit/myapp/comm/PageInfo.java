package kr.ac.hit.myapp.comm;

public class PageInfo {
	//사용자로부터 입력받는 값들
	private int page = 1; //현재 페이지 번호
	private int size = 5; //한 페이지에 출력할 게시물 수	
	private int pageSize = 5; //페이지 리스트(링크)에 출력할 페이지 수	
	private int totalRecordCount; //전체 게시물 수
	
	//입력받은 값들을 사용하여 계산하는 값들
	private int totalPageCount; //전체 페이지 수
	private int firstPageNoOnPageList; //현재 출력되는 페이지 리스트의 첫 페이지 번호
	private int lastPageNoOnPageList;  //현재 출력되는 페이지 리스트의 마지막 페이지 번호
	private int firstRecordIndex; //데이터베이스에서 조회해 올 첫번째 게시물 번호
	private int lastRecordIndex; //데이터베이스에서 조회해 올 마지막 게시물 번호 
	
	private String pageHtml; //화면에 출력할 페이지 리스트(링크들) HTML
	
	public void makePageHtml() {
		totalPageCount = ((totalRecordCount-1)/size) + 1;
		firstPageNoOnPageList = ((page-1)/pageSize)*pageSize + 1;
		lastPageNoOnPageList = firstPageNoOnPageList+pageSize-1;
		if(lastPageNoOnPageList>totalPageCount){ lastPageNoOnPageList=totalPageCount; }
		firstRecordIndex = (page - 1) * size;
		lastRecordIndex = page * size;
		
		//현재 페이지 리스트에 첫 페이지가 있는 경우, '처음'은 링크가 걸리지 않도록
		//현재 페이지 리스트에 마지막 페이지가 있는 경우, '마지막'은 링크가 걸리지 않도록
		//이전 페이지 리스트가 없는 경우, '이전'은 링크가 걸리지 않도록
		//다음 페이지 리스트가 없는 경우, '다음'은 링크가 걸리지 않도록
		String html = "";
		if (firstPageNoOnPageList!=1) { //페이지리스트의 첫 페이지가 1페이지가 아닌 경우
			html += "<a href='#' onclick='goPage(1);' >처음</a> | ";
		}
		if (firstPageNoOnPageList!=1) {
			html += "<a href='#' onclick='goPage(" + (firstPageNoOnPageList-1) + ");' >이전</a> | ";
		}else {
			html += "이전 | ";
		}
		for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
			if ( i == page ) { //출력하는 페이지 번호가 현재 보고있는 페이지 번호인 경우
				html += i + " | ";
			}else {
				html += "<a href='#' onclick='goPage(" + i + ");' >" + i + "</a> | ";
			}
		}
		//페이지리스트의 마지막 페이지번호가 총 페이지수보다 작을 때
		if (lastPageNoOnPageList < totalPageCount) { 
			html += "<a href='#' onclick='goPage(" + (lastPageNoOnPageList+1) + ");' >다음</a> | ";
		}else {
			html += "다음";			
		}
		if (lastPageNoOnPageList < totalPageCount) {
			html += "<a href='#' onclick='goPage(" + totalPageCount + ");' >마지막</a>";
		}
		html += "<script>function goPage(no) {location.href = location.pathname + '?page='+no;}</script>";

		pageHtml = html;
	}
	
	public String getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getFirstPageNoOnPageList() {
		return firstPageNoOnPageList;
	}
	public void setFirstPageNoOnPageList(int firstPageNoOnPageList) {
		this.firstPageNoOnPageList = firstPageNoOnPageList;
	}
	public int getLastPageNoOnPageList() {
		return lastPageNoOnPageList;
	}
	public void setLastPageNoOnPageList(int lastPageNoOnPageList) {
		this.lastPageNoOnPageList = lastPageNoOnPageList;
	}
	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}
	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}
	public int getLastRecordIndex() {
		return lastRecordIndex;
	}
	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}
}









