package com.showtime.authserver.bean.request;

import lombok.Data;

/**
 * @author Vengatesan Nagarajan
 */
@Data
public class UserSearchRequest {

    private int pageNo;
    private int recordsCount;

}
