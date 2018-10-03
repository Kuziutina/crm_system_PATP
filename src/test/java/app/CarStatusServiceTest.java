package app;

import app.service.CarStatusService;
import app.service.interfaces.CarServiceInt;
import app.service.interfaces.CarStatusServiceInt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarStatusServiceTest {
    private CarStatusServiceInt carStatusService;

    @Before
    public void init() {
        carStatusService = mock(CarStatusService.class);
    }

    @Test
    public void getAllCarStatusTest() {
        List list = mock(List.class);
        when(carStatusService.getAllCarStatuses()).thenReturn(list);
        Assert.assertEquals(list, carStatusService.getAllCarStatuses());
    }
}

