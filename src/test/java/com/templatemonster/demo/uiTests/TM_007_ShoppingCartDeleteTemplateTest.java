package com.templatemonster.demo.uiTests;

import com.templatemonster.demo.baseTests.TemplateMonsterBaseTest;

/**
 * Тестовый сценарий:
 * Предусловие: в корзине пользователя хранятся продукты (проверка мерджа корзин)
 * 1. Незалогиненным пользователем положить в корзину продукты
 * 2. Авторизоваться на степе 1 чекаута
 * 3. Перейти на страницу шоппинг карты и удалить некоторые продукты из корзины (нажать крестик возле выбранного продукта)
 * <p>
 * Ожидаемый результат:
 * 1. В блоке Cart Summary удаляются соответствующие продукты
 * 2. Subtotal: и Order Total: пересчитываются с вычетом стоимости удалённых продуктов
 */
public class TM_007_ShoppingCartDeleteTemplateTest extends TemplateMonsterBaseTest{
}
