class SantaString(str):

    def is_nice(self):
        #Why do we use 'all' here, besides nice syntax?
        #Do we still get that benefit if we don't use map?
        return all((x() for x in [self._has_three_vowels, self._has_double_letter, self._no_bad_substring]))

    def is_naughty(self):
        return not self.is_nice()

    def _has_three_vowels(self):
        vowel_count = sum(1 for char in self if char in self.VOWELS)
        return vowel_count >= 3

    def _has_double_letter(self):
        for i in range(len(self) - 1):
            if self[i] == self[i + 1]:
                return True
        return False

    def _no_bad_substring(self):
        #Why do we use 'any' here?
        return not any(ss in self for ss in self.BAD_SUBSTRINGS)

    #Why do we define these variable here?
    #Using all-caps for this type of variable is a convention, what does it signify?
    #Why do we use a frozenset?
    VOWELS = "aeiou"
    BAD_SUBSTRINGS = frozenset(['ab', 'cd', 'pq', 'xy'])

