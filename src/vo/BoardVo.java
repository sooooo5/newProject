package vo;

import lombok.Data;

@Data
public class BoardVo {
	 private int board_no;
	 private String board_title;
	 private String board_content;
	 private int board_price;
	 private String board_date;
	 private int board_views;
	 private String board_stat;
	 private int board_like;
	 private String delyn;
	 private String mem_buyer;
	 private String mem_seller;
	 private int cate_id;
}
