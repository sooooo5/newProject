package vo;

import lombok.Data;

@Data
public class MessageVo {
	 private int message_id;
	 private String message_content;
	 private String message_date;
	 private int chat_no;
	 private String mem_id;
	 private String mem_id2;
	 private int rn;
	 private String mem_nick;
}
