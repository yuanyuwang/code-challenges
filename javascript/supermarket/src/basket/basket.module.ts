import { ProductsModule } from '../products/products.module';
import { Module } from '@nestjs/common';
import { BasketController } from './basket.controller';
import { BasketService } from './basket.service';

@Module({
  imports: [ProductsModule],
  controllers: [BasketController],
  providers: [BasketService],
})
export class BasketModule {}
