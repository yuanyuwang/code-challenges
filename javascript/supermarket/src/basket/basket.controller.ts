import { BasketService } from './basket.service';
import { Controller, Put } from '@nestjs/common';
import { BasketItemDto } from './dto/basketItem.dto';

@Controller('basket')
export class BasketController {
  constructor(private basketService: BasketService) {}

  @Put()
  calculate(basket: BasketItemDto[]) {
    const total = this.basketService.calculate(basket);
    return {
      totalBeforeDiscounts: total,
      discounts: 0,
      grandTotal: total,
    };
  }
}
