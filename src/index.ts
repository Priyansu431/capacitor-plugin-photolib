import { registerPlugin } from '@capacitor/core';

import type { PhotolibPlugin } from './definitions';

const Photolib = registerPlugin<PhotolibPlugin>('Photolib', {
  web: () => import('./web').then(m => new m.PhotolibWeb()),
});

export * from './definitions';
export { Photolib };
