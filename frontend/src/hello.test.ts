import { describe, it, expect } from 'vitest'

describe('Hello World Test', () => {
  it('should return hello world', () => {
    const message = 'Hello World'
    expect(message).toBe('Hello World')
  })

  it('should always pass', () => {
    expect(true).toBe(true)
  })
})