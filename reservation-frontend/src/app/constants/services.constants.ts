export const AVAILABLE_SERVICES = [
  'Corte de pelo',
  'Manicura',
  'Pedicura',
  'Masaje relajante',
  'Tratamiento facial',
  'Coloración',
] as const;

export type AvailableService = (typeof AVAILABLE_SERVICES)[number];
