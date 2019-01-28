

package com.kucoin.sdk.rest.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by zicong.lu on 2018/12/21.
 */
@Data
@NoArgsConstructor
public class Pagination<T> {

    private Integer currentPage;
    private Integer pageSize;
    private Long totalNum;
    private Integer totalPage;
    private List<T> items;

    public Pagination(Integer currentPage, Integer pageSize, Long totalNum, List<T> items) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        this.items = items;
        this.totalPage = (int)((totalNum + (long)pageSize - 1L) / (long)pageSize);
    }

}
