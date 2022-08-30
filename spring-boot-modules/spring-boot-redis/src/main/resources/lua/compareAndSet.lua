--
-- Created by IntelliJ IDEA.
-- User: xingcheng.yin
-- Date: 2020/5/25
-- Time: 19:31
-- To change this template use File | Settings | File Templates.
--

if redis.call('GET', KEYS[1]) ~= ARGV[1] then
    return 0
end
redis.call('SET', KEYS[1], ARGV[2])
return 1

--if redis.call('GET', KEYS[1]) != ARGV[1] then
--return {0}
--end
--redis.call('SET', KEYS[2], ARGV[2])
--return {1}