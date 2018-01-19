package com.kangyonggan.metadata;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 没有事务
 *
 * @author kangyonggan
 * @since 2016/11/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-core.xml"})
public abstract class AbstractServiceTest extends AbstractJUnit4SpringContextTests {

}
