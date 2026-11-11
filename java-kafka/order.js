import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '10s', target: 0 },
    ],
};

export default function () {
    const body = JSON.stringify({
        userId: `u-${__VU}`,
        productName: 'Shoes',
        productTag: 'running',
        productQty: 1,
    });
    const res = http.post('http://localhost:8081/orders', body, {
        headers: { 'Content-Type': 'application/json' },
    });
    check(res, { 'order created': (r) => r.status === 201 });
    sleep(0.2);
}