package weteam.backend.domain.common.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Getter
public enum PaginationSortType {
    DONE("done"),
    EMPTY(null);

    private final String field;

    public Sort toSort(Sort.Direction direction) {
        return Sort.by(direction, field);
    }
}