package net.hackbee.interview.carparts.sales;

import net.hackbee.interview.carparts.persistence.SaleArgumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {

    @Mock
    private SaleArgumentRepository saleArgumentRepositoryMock;

    @InjectMocks
    private SaleService subject;

    @Test
    void trueWhenMoreThenZeroResults() {
        given(saleArgumentRepositoryMock.deleteByPart_Id(1L)).willReturn(1);

        assertThat(subject.cleanByPart(1L)).isTrue();
    }

    @Test
    void falseWhenZeroResults() {
        given(saleArgumentRepositoryMock.deleteByPart_Id(1L)).willReturn(0);

        assertThat(subject.cleanByPart(1L)).isFalse();
    }
}